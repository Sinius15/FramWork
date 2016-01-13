package test;

import it.sijmen.webframework.framwork.Framwork;
import it.sijmen.webframework.framwork.routing.RouteFilter;
import it.sijmen.webframework.framwork.routing.RouteGroup;
import it.sijmen.webframework.webserver.Request;


/**
 * Created by Sijmen on 12-1-2016.
 */
public class TestFramework extends Framwork {

    @Override
    public void initControllers() {

    }

    @Override
    public void initRoutes(RouteGroup router) {
        ExampleController controller = new ExampleController();

        router.group( group -> {
            group.name("Authorized");
            group.onlyWhen(request -> request.getDomain().contains(".dev"));
            group.get("/", controller::test);
        });

        router.get("/", controller::index);
    }
}
