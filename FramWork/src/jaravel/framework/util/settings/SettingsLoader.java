package jaravel.framework.util.settings;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Sijmen on 4-2-2016.
 */
public abstract class SettingsLoader {

    public abstract HashMap<String, Object> loadSettings(File file);

}
