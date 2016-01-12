package test;

import it.sijmen.webframework.framwork.Framwork;
import it.sijmen.webframework.framwork.routing.Router;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class TestFramework extends Framwork {

    @Override
    public void initRoutes(Router router) {
        router.get("/", ExampleController.class, "index");
    }

}
