package jaravel.framework.web;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class Response {

    public int statusCode = 200;

    public String data = "hello world";

    public Response(int statusCode, String data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public Response(int statusCode) {
        this.statusCode = statusCode;
    }

    public Response(String data) {
        this.data = data;
    }

    public Response() {
    }

    public String render() {
        return data;
    }

}
