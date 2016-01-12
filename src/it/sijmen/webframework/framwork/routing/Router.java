package it.sijmen.webframework.framwork.routing;

import it.sijmen.webframework.framwork.mvc.Controller;
import it.sijmen.webframework.framwork.mvc.ControllerFunction;
import it.sijmen.webframework.webserver.Request;
import it.sijmen.webframework.webserver.RequestType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class Router {

    private ArrayList<Route> routeList = new ArrayList<>();

    private Route[] routeListSolid = null;

    public Route routeRequest(Request request){
        for(Route route : routeListSolid){
            if(route.accepts(request)){
                return route;
            }
        }
        return null;
    }

    public void finishSetup(){
        routeListSolid = routeList.toArray(new Route[routeList.size()]);
    }

    public void addRoute(Route route){
        if(routeListSolid != null)
            throw new IllegalStateException("Can not add route when routes are made solid");
        if(route == null)
            throw new IllegalArgumentException("Rout may not be null");
        routeList.add(route);
    }

    public void addRoute(String subdomain, String domain, String uri, RequestType requestType, ControllerFunction method){
        this.addRoute(new Route(subdomain, domain, uri, requestType, method));
    }

    public void addRoute(String domain, String uri, RequestType requestType, ControllerFunction method){
        this.addRoute(null, domain, uri, requestType, method);
    }

    public void addRoute(String uri, RequestType requestType, ControllerFunction method){
        this.addRoute(null, uri, requestType, method);
    }

    public void get(String uri, ControllerFunction method){
        this.addRoute(uri, RequestType.GET, method);
    }

    public void post(String uri, ControllerFunction method){
        this.addRoute(uri, RequestType.POST, method);
    }

    public static String normalize(String input){
        if(input == null)
            return null;
        input = input.trim();
        if(input.endsWith("/"))
            input = input.substring(0, input.length()-1);
        if(input.startsWith("/"))
            input = input.substring(1);
        input = input.toLowerCase();
        return input;
    }

    public Route[] getRouteList() {
        return routeListSolid;
    }
}
