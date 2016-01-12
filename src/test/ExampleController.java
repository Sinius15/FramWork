package test;

import it.sijmen.webframework.framwork.mvc.Controller;
import it.sijmen.webframework.webserver.Request;
import it.sijmen.webframework.webserver.Response;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class ExampleController extends Controller {

    public Response index(Request request){
        return new Response("Hello World from Index!");
    }

    public Response test(Request request){
        return new Response("Hello from Test");
    }

    public Response number(Request request){
        return new Response("Hello from Number " + request.getUrlParamter("nr", "No"));
    }

}
