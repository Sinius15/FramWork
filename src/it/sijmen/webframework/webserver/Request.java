package it.sijmen.webframework.webserver;

import com.sun.net.httpserver.Headers;
import it.sijmen.webframework.framwork.routing.Router;

import java.net.URI;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class Request {

    private String subdomain;
    private String domain;
    private URI uri;
    private String uriString;

    private RequestType requestType;
    private Headers headers;

    public Request(String domain, RequestType requestType, URI uri, Headers headers) {
        this.domain = Router.normalize(domain);
        this.requestType = requestType;
        this.uri = uri;
        this.uriString = uri.toString();
        this.headers = headers;
    }

    public String getDomain() {
        return domain;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public URI getUri() {
        return uri;
    }

    public String getUriString() {
        return uriString;
    }

    public Headers getHeaders() {
        return headers;
    }

    public String getSubdomain() {
        return subdomain;
    }
}
