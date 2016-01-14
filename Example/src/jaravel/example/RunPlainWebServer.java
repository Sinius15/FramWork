package jaravel.example;

import jaravel.deployment.plain.PlainWebServer;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class RunPlainWebServer {

    public static void main(String[] args) {
        new PlainWebServer(new TestFramework());
    }

}
