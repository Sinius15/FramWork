package it.sijmen.webframework.framwork;

import it.sijmen.webframework.framwork.routing.Route;
import it.sijmen.webframework.framwork.routing.RouteGroup;
import it.sijmen.webframework.webserver.Request;
import it.sijmen.webframework.webserver.Response;

/**
 * Created by Sijmen on 12-1-2016.
 */
public abstract class Framwork {

    public abstract void initControllers();
    public abstract void initRoutes(RouteGroup router);

    private RouteGroup router = new RouteGroup();

    public Framwork() {
        initControllers();
        router.name("Root Router Group");
        initRoutes(router);
        router.finishSetup();
    }

    public Response handle(Request request){
        Route route = router.routeRequest(request);

        if(route == null)
            return abort(401, "Page not found");

        request.boundToRoute(route);

        try {
            return route.getMethod().doAction(request);
        } catch (Exception e) {
            e.printStackTrace();
            return abort(500, "Internal server error: Something went wrong in the controller.");
        }
    }

    public Response abort(int statusCode, String message){
        return new Response(statusCode, message);
    }
}
