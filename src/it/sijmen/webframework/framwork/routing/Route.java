package it.sijmen.webframework.framwork.routing;

import it.sijmen.webframework.framwork.mvc.ControllerFunction;
import it.sijmen.webframework.framwork.util.RegExE;
import it.sijmen.webframework.framwork.util.StringUtil;
import it.sijmen.webframework.webserver.Request;
import it.sijmen.webframework.webserver.RequestType;

import java.util.ArrayList;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class Route {

    protected String domain;

    protected String uri;

    protected RequestType requestType;

    protected ControllerFunction method;

    protected RouteFilter customFilter;

    public Route(String domain, String uri, RequestType requestType, RouteFilter filter, ControllerFunction method) {
        this.domain = StringUtil.normalize(domain);
        this.uri = StringUtil.normalize(uri);

        this.requestType = requestType;
        this.method = method;
        this.customFilter = filter;
    }

    public boolean accepts(Request request) {
        if(this.getRequestType() != null)
            if(request.getRequestType() != this.getRequestType())
                return false;
        if(this.domain != null)
            if(!RegExE.matches(this.getDomain(), request.getDomain()))
                return false;
        if(this.getUri() != null)
            if(!RegExE.matches(this.getUri(), request.getUriString()))
                return false;
        if(customFilter != null)
            if(!customFilter.accepts(request))
                return false;
        return true;
    }

    public RouteFilter getFilter() {
        return customFilter;
    }

    public ControllerFunction getMethod() {
        return method;
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
