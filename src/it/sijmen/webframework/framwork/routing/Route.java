package it.sijmen.webframework.framwork.routing;

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

    private Class controller;
    private Method method;

    public Route(String subdomain, String domain, String uri, RequestType requestType, Class controller, String method) {
        this.subdomain = Router.normalize(subdomain);
        this.domain = Router.normalize(domain);
        this.uri = Router.normalize(uri);

        this.requestType = requestType;
        this.controller = controller;

        try {
            this.method = controller.getMethod(method);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Route method not found");
        }
    }

    public Method getMethod() {
        return method;
    }

    public boolean accepts(Request request) {
        if(request.getRequestType() != this.getRequestType())
            return false;
        if(this.domain != null)
            if(!request.getDomain().matches(this.getDomain()))
                return false;
        if(this.getSubdomain() != null)
            if(!request.getSubdomain().matches(this.getSubdomain()))
                return false;
        if(this.getUri() != null)
            if(!request.getUriString().matches(this.getUri()))
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

    public Class getController() {
        return controller;
    }
}
