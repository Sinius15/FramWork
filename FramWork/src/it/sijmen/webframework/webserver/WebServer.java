package it.sijmen.webframework.webserver;

import com.sun.net.httpserver.HttpServer;
import it.sijmen.webframework.framwork.Framwork;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class WebServer {

    public static final int SERVER_PORT = 8910;

    private HttpServer server = null;

    public WebServer(Framwork framwork) {
        try {
            server = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
            server.createContext("/", new HttpRequestHandler(framwork));
            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        server.stop(1);
    }
}
