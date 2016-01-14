package jaravel.framework;

import jaravel.framework.database.DatabaseEngine;
import jaravel.framework.database.engines.MySqlEngine;
import jaravel.framework.routing.Route;
import jaravel.framework.routing.RouteGroup;
import jaravel.framework.web.Request;
import jaravel.framework.web.Response;

/**
 * Created by Sijmen on 12-1-2016.
 */
public abstract class Jaravel {

    public abstract void init();
    public abstract void initRoutes(RouteGroup router);

    private RouteGroup router;
    private DatabaseEngine engine;

    public Jaravel() {
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
