package jaravel.framework.routing;

import jaravel.framework.web.Request;

/**
 * Created by Sijmen on 13-1-2016.
 */
public interface RouteFilter {

    boolean accepts(Request request);

}
