package it.sijmen.webframework.webserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.sijmen.webframework.framwork.Framwork;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class HttpRequestHandler implements HttpHandler {

    private Framwork framwork;

    public HttpRequestHandler(Framwork framwork) {
        this.framwork = framwork;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        URI uri = httpExchange.getRequestURI();
        RequestType requestType = RequestType.fromString(httpExchange.getRequestMethod());

        Headers headers = httpExchange.getRequestHeaders();
        String domain;

        if(headers.containsKey("Host")){
            List<String> domains = headers.get("Host");
            if(domains.size() != 1) {
                abort(httpExchange, 400, "Invalid host field.");
                return;
            }
            domain = domains.get(0);
            if(domain == null || domain.trim().equals("")){
                abort(httpExchange, 400, "Empty host field.");
                return;
            }
        }else{
            abort(httpExchange, 400, "No host field found.");
            return;
        }

        Request request = new Request(domain, requestType, uri, headers);
        Response response;
        try{
            response = framwork.handle(request);
        }catch(Exception e){
            e.printStackTrace();
            abort(httpExchange, 500, "Framewokr could not handle request.");
            return;
        }


        String responseContent = response.render();
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        httpExchange.sendResponseHeaders(200, responseContent.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(responseContent.getBytes());
        os.close();
    }

    public void abort(HttpExchange exchange, int statusCode, String message) throws IOException {
        String response = "Error code " + statusCode + ": " + message;
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
