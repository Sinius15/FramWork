package jaravel.framework;

import jaravel.framework.database.DatabaseEngine;
import jaravel.framework.database.connectors.DatabaseConnector;
import jaravel.framework.database.connectors.JDBCConnector;
import jaravel.framework.routing.Route;
import jaravel.framework.routing.RouteGroup;
import jaravel.framework.util.DependencyInjector;
import jaravel.framework.util.settings.Settings;
import jaravel.framework.web.Request;
import jaravel.framework.web.Response;

import java.io.File;
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

    public static DependencyInjector dependencyInjector;

    private Settings settings;

    public Jaravel() {
        try {
            dependencyInjector = new DependencyInjector();

            init();

            settings = createSettings();

            router = new RouteGroup();
            router.name("Root Router Group");
            initRoutes(router);
            router.finishSetup();

            dependencyInjector.putValue(Settings.class, settings);
            dependencyInjector.putValue(DatabaseConnector.class, connection);
            dependencyInjector.putValue(DependencyInjector.class, dependencyInjector);

            engine = (DatabaseEngine) dependencyInjector.getObjectMagicly(settings.database_engine) ;
            dependencyInjector.putValue(DatabaseEngine.class, engine);

            connection = (DatabaseConnector) dependencyInjector.getObjectMagicly(settings.database_connector);
            dependencyInjector.putValue(DatabaseConnector.class, connection);

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
        return new Settings(new File("res/settings.json"));
    }

    public static DatabaseEngine getDatabaseEngine() {
        return engine;
    }

    public static DatabaseConnector getDatabaseConnection() {
        return connection;
    }


}
