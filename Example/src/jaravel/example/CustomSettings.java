package jaravel.example;

import jaravel.framework.Settings;

/**
 * Created by Sijmen on 2-2-2016.
 */
public class CustomSettings extends Settings {

    public CustomSettings(){
        database_jdbc_connection = "jdbc:mysql://localhost:3306/test?user=tester&password=123456&useSSL=false";
    }

}
