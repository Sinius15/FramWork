package it.sijmen.webframework.webserver;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class Response {

    public int responseCode = 200;

    public String data = "hello world";

    public Response(int responseCode, String data) {
        this.responseCode = responseCode;
        this.data = data;
    }

    public Response(int responseCode) {
        this.responseCode = responseCode;
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
