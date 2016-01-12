package it.sijmen.webframework.framwork;

import it.sijmen.webframework.framwork.mvc.Controller;
import it.sijmen.webframework.framwork.routing.Route;
import it.sijmen.webframework.framwork.routing.Router;
import it.sijmen.webframework.webserver.Request;
import it.sijmen.webframework.webserver.Response;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by Sijmen on 12-1-2016.
 */
public abstract class Framwork {

    private HashMap<String, Controller> controllerInstances;

    public abstract void initRoutes(Router router);

    private Router router = new Router();

    public Framwork() {
        initRoutes(router);
        router.finishSetup();
        controllerInstances = createControllerInstances();
    }

    public Response handle(Request request){
        Route route = router.routeRequest(request);
        if(route == null)
            return abort(401, "Page not found");

        Controller controllerInstance = controllerInstances.get(route.getController().toGenericString());
        try {
            return (Response) route.getMethod().invoke(controllerInstance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalStateException("Something went wrong that should not go wrong.");
        }
    }

    public Response abort(int statusCode, String message){
        return new Response(statusCode, message);
    }

    private HashMap<String, Controller> createControllerInstances() {
        HashMap<String, Controller> map = new HashMap<>();
        for(Route r : router.getRouteList()){
            String key  = r.getController().toGenericString();
            if(map.containsKey(key))
                continue;

            try {
                Constructor<?> constructor = r.getController().getConstructor();
                Object object = constructor.newInstance();
                map.put(key, (Controller)object);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
