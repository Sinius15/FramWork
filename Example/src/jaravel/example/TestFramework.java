package jaravel.example;

import jaravel.framework.Jaravel;
import jaravel.framework.routing.RouteGroup;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class TestFramework extends Jaravel {

    @Override
    public void init() {
        System.out.println("Initialized FramWork");
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
