package jaravel.example;

import jaravel.framework.util.settings.Settings;

import java.io.File;

/**
 * Created by Sijmen on 2-2-2016.
 */
public class CustomSettings extends Settings {

    public CustomSettings(){
        super(new File("Example/res/settings.json"));
        database_jdbc_connection = (String) setting("database_connection");
    }

}
