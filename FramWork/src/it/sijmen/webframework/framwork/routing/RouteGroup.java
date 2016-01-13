package it.sijmen.webframework.framwork.routing;

import it.sijmen.webframework.framwork.mvc.ControllerFunction;
import it.sijmen.webframework.webserver.Request;
import it.sijmen.webframework.webserver.RequestType;

import java.util.ArrayList;

/**
 * Created by Sijmen on 13-1-2016.
 */
public class RouteGroup extends Route{

    private ArrayList<Route> routeList = new ArrayList<>();
    private ArrayList<RouteGroup> routeGroupList = new ArrayList<>();

    private Route[] routeListSolid = null;
    private RouteGroup[] routeGroupListSolid = null;

    private String groupName;

    public RouteGroup() {
        super(null, null, null, null, null);
    }


    public Route routeRequest(Request request){

        if(!this.accepts(request))
            return null;

        for(RouteGroup route : routeGroupListSolid){
            Route r;
            if((r = route.routeRequest(request)) != null){
                return r;
            }
        }

        for(Route route : routeListSolid){
            if(route.accepts(request)){
                return route;
            }
        }
        return null;
    }

    public void finishSetup(){
        routeListSolid = routeList.toArray(new Route[routeList.size()]);
        routeGroupListSolid = routeGroupList.toArray(new RouteGroup[routeGroupList.size()]);
        for(RouteGroup r : routeGroupListSolid)
            r.finishSetup();
        routeList = null;
        routeGroupList = null;
    }

    public void addGroup(RouteGroup group){
        if(routeList == null)
            throw new IllegalStateException("Can not add routegroup when routes are made solid");
        if(group == null)
            throw new IllegalArgumentException("Routegroup may not be null");
        routeGroupList.add(group);
    }

    public void group(RouteGroupFiller filler){
        RouteGroup r = new RouteGroup();
        filler.fillRouteGroup(r);
        this.addGroup(r);
    }

    public void addRoute(Route route){
        if(routeList == null)
            throw new IllegalStateException("Can not add route when routes are made solid");
        if(route == null)
            throw new IllegalArgumentException("Rout may not be null");
        routeList.add(route);
    }

    public void addRoute(String domain, String uri, RequestType requestType, RouteFilter customFilter, ControllerFunction method){
        this.addRoute(new Route(domain, uri, requestType, customFilter, method));
    }

    public void addRoute(String domain, String uri, RequestType requestType, ControllerFunction method){
        this.addRoute(domain, uri, requestType, null, method);
    }

    public void get(String uri, ControllerFunction method){
        this.addRoute(null, uri, RequestType.GET, method);
    }

    public void get(String uri, RouteFilter filter, ControllerFunction method){
        this.addRoute(null, uri, RequestType.GET, filter, method);
    }

    public void post(String uri, ControllerFunction method){
        this.addRoute(null, uri, RequestType.POST, method);
    }

    public void post(String uri, RouteFilter filter, ControllerFunction method){
        this.addRoute(null, uri, RequestType.POST, filter, method);
    }



    public void onlyDomain(String domain) {
        this.domain = domain;
    }

    public void onlyUri(String uri) {
        this.uri = uri;
    }

    public void onlyRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public void onlyWhen(RouteFilter filter){
        this.customFilter = filter;
    }

    public String getGroupName() {
        return groupName;
    }

    public void name(String groupName) {
        this.groupName = groupName;
    }
}
