package it.sijmen.webframework.framwork.routing;

import it.sijmen.webframework.webserver.Request;

/**
 * Created by Sijmen on 13-1-2016.
 */
public interface RouteFilter {

    boolean accepts(Request request);

}
