package jaravel.framework.web;

/**
 * Created by Sijmen on 12-1-2016.
 */
public enum RequestType {

    GET,
    HEAD,
    POST,
    PUT,
    DELETE,
    CONNECT,
    OPTIONS,
    TRACE;

    public static RequestType fromString(String requestMethod) {
        switch (requestMethod.toUpperCase()){
            case "GET": return GET;
            case "HEAD": return HEAD;
            case "POST": return POST;
            case "PUT": return PUT;
            case "DELETE": return DELETE;
            case "CONNECT": return CONNECT;
            case "OPTIONS": return OPTIONS;
            case "TRACE": return TRACE;
            default: return GET;
        }
    }
}
