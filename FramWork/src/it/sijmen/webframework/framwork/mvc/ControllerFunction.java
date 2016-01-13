package it.sijmen.webframework.framwork.mvc;

import it.sijmen.webframework.webserver.Request;
import it.sijmen.webframework.webserver.Response;

/**
 * Created by Sinius on 12-1-2016.
 */
public interface ControllerFunction {

    Response doAction(Request args);

}
