package jaravel.example;

import jaravel.framework.Jaravel;
import jaravel.framework.database.connectors.JDBCConnector;
import jaravel.framework.database.engines.MySqlEngine;
import jaravel.framework.routing.RouteGroup;

import java.io.IOException;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class TestFramework extends Jaravel {

    @Override
    public void init() {
        System.out.println("Initialized FramWork");
        engine = new MySqlEngine();
        try {
            connection = new JDBCConnector("com.mysql.jdbc.Driver");
            connection.connect("jdbc:mysql://localhost:3306/test?user=tester&password=123456&useSSL=false");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
