package jaravel.framework.mvc;

import jaravel.framework.web.Request;
import jaravel.framework.web.Response;

/**
 * Created by Sinius on 12-1-2016.
 */
public interface ControllerFunction {

    Response doAction(Request args);

}
