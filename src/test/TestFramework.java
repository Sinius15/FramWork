package test;

import it.sijmen.webframework.framwork.Framwork;
import it.sijmen.webframework.framwork.routing.Router;
import it.sijmen.webframework.framwork.singleton.Single;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class TestFramework extends Framwork {

    @Override
    public void initControllers() {
        Single.put(new ExampleController());
    }

    @Override
    public void initRoutes(Router router) {
        ExampleController controller = new ExampleController();

        router.get("/", controller::index);
        router.get("/test", controller::test);
        router.get("/nr/{nr}", controller::number);
    }

}
