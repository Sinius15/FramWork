package test;

import it.sijmen.webframework.webserver.WebServer;
import test.TestFramework;

/**
 * Created by Sijmen on 12-1-2016.
 */
public class Main {

    public static void main(String[] args) {
        new WebServer(new TestFramework());
    }

}
