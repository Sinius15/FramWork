package jaravel.example;

import jaravel.example.model.User;
import jaravel.framework.database.builder.WhereClause;
import jaravel.framework.mvc.Controller;
import jaravel.framework.web.Request;
import jaravel.framework.web.Response;

import java.io.IOException;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class ExampleController extends Controller {

    public Response index(Request request){
        try {
            User user = new User(3);
            return new Response("Hello " + user.name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response("Hello unknown user");
    }

    public Response test(Request request){
        return new Response("Hello from Test");
    }

    public Response number(Request request){
        return new Response("Hello from Number " + request.getUrlParamter("nr", "No"));
    }

}
