package jaravel.example;

import jaravel.example.model.UserModel;
import jaravel.framework.mvc.Controller;
import jaravel.framework.web.Request;
import jaravel.framework.web.Response;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class ExampleController extends Controller {

    public Response index(Request request){
        UserModel user = new UserModel(10);

        return new Response("Hello " + user.name);
    }

    public Response test(Request request){
        return new Response("Hello from Test");
    }

    public Response number(Request request){
        return new Response("Hello from Number " + request.getUrlParamter("nr", "No"));
    }

}
