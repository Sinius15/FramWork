package jaravel.framework.web;

import jaravel.framework.routing.Route;
import jaravel.framework.util.RegExE;
import jaravel.framework.util.StringUtil;

import java.net.URI;
import java.util.HashMap;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class Request {

    private String domain;
    private URI uri;
    private String uriString;

    private RequestType requestType;
    private Headers headers;

    private Route route;
    private HashMap<String, String> urlParameters;

    public Request(String domain, RequestType requestType, URI uri, Headers headers) {
        this.domain = StringUtil.normalize(domain);
        this.requestType = requestType;
        this.uri = uri;
        this.uriString = StringUtil.normalize(uri.toString());
        this.headers = headers;
    }

    public HashMap<String, String> getUrlParamters(){
        if(urlParameters == null)
            throw new IllegalStateException("Request not yet bound to route");
        return urlParameters;
    }

    public String getUrlParamter(String key, String defaultt){
        return this.getUrlParamters().getOrDefault(key, defaultt);
    }

    public void boundToRoute(Route route) {
        if(this.route != null)
            throw new IllegalStateException("Route can only be set once.");
        this.route = route;
        this.urlParameters = RegExE.decodeNamedGroup(route.getUri(), this.getUriString());
    }

    public String getUrlParamter(String key){
        return this.getUrlParamters().get(key);
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
}
