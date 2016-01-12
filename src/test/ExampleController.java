package test;

import it.sijmen.webframework.framwork.mvc.Controller;
import it.sijmen.webframework.webserver.Response;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class ExampleController extends Controller{

    public Response index(){
        return new Response("Hello World from Index!");
    }

}
