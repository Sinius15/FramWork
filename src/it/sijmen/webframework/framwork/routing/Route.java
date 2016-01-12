package it.sijmen.webframework.framwork.routing;

import it.sijmen.webframework.framwork.mvc.ControllerFunction;
import it.sijmen.webframework.framwork.util.RegExE;
import it.sijmen.webframework.webserver.Request;
import it.sijmen.webframework.webserver.RequestType;

import java.lang.reflect.Method;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class Route {

    private String subdomain;

    private String domain;

    private String uri;

    private RequestType requestType;

    private ControllerFunction method;

    public Route(String subdomain, String domain, String uri, RequestType requestType, ControllerFunction method) {
        this.subdomain = Router.normalize(subdomain);
        this.domain = Router.normalize(domain);
        this.uri = Router.normalize(uri);

        this.requestType = requestType;
        this.method = method;
    }

    public ControllerFunction getMethod() {
        return method;
    }

    public boolean accepts(Request request) {
        if(request.getRequestType() != this.getRequestType())
            return false;
        if(this.domain != null)
            if(!RegExE.matches(this.getDomain(), request.getDomain()))
                return false;
        if(this.getSubdomain() != null)
            if(!RegExE.matches(this.getSubdomain(), request.getSubdomain()))
                return false;
        if(this.getUri() != null)
            if(!RegExE.matches(this.getUri(), request.getUriString()))
                return false;
        return true;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public String getDomain() {
        return domain;
    }

    public String getUri() {
        return uri;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
