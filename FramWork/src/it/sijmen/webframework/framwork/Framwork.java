package it.sijmen.webframework.framwork;

import it.sijmen.webframework.framwork.database.DatabaseEngine;
import it.sijmen.webframework.framwork.database.engines.MySqlEngine;
import it.sijmen.webframework.framwork.routing.Route;
import it.sijmen.webframework.framwork.routing.RouteGroup;
import it.sijmen.webframework.webserver.Request;
import it.sijmen.webframework.webserver.Response;

/**
 * Created by Sijmen on 12-1-2016.
 */
public abstract class Framwork {

    public abstract void init();
    public abstract void initRoutes(RouteGroup router);

    private RouteGroup router;
    private DatabaseEngine engine;

    public Framwork() {
        init();
        if(engine == null)
            engine = new MySqlEngine();
        router = new RouteGroup();
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

    public void setDatabaseEngine(DatabaseEngine engine) {
        this.engine = engine;
    }
}
