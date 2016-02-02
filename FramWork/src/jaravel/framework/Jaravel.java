package jaravel.framework;

import jaravel.framework.database.DatabaseEngine;
import jaravel.framework.database.connectors.DatabaseConnector;
import jaravel.framework.database.connectors.JDBCConnector;
import jaravel.framework.database.engines.MySqlEngine;
import jaravel.framework.routing.Route;
import jaravel.framework.routing.RouteGroup;
import jaravel.framework.util.ReflectHelper;
import jaravel.framework.web.Request;
import jaravel.framework.web.Response;
import sun.plugin.javascript.ReflectUtil;

import java.io.IOException;

/**
 * Created by Sijmen on 12-1-2016.
 */
public abstract class Jaravel {

    public abstract void init();
    public abstract void initRoutes(RouteGroup router);

    private RouteGroup router;

    protected static DatabaseEngine engine;
    protected static DatabaseConnector connection;

    private Settings settings;

    public Jaravel() {
        try {
            init();

            settings = createSettings();

            router = new RouteGroup();
            router.name("Root Router Group");
            initRoutes(router);
            router.finishSetup();

            engine = (DatabaseEngine) ReflectHelper.getEmptyObject(settings.database_engine);

            connection = (JDBCConnector) ReflectHelper.getEmptyObject(settings.database_connector,
                    new Class<?>[] {Settings.class},
                    new Object[]{settings});
            if(connection == null)
                throw new IOException("No database connection.");

            connection.connect();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(500);
        }
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

    public Settings createSettings(){
        return new Settings();
    }

    public static DatabaseEngine getDatabaseEngine() {
        return engine;
    }

    public static DatabaseConnector getDatabaseConnection() {
        return connection;
    }


}
