package jaravel.framework.util.settings;

import jaravel.framework.util.StringUtil;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Sijmen on 4-2-2016.
 */
public class JSONSettingsLoader extends SettingsLoader{

    /**
     * This implemenation of the SettingsLoader loads the
     * given file as json.
     * @return The loaded settings as a HashMap
     * @param file The file to load.
     */
    @Override
    public HashMap<String, Object> loadSettings(File file) {
        String content;
        try {
            content = StringUtil.readFile(file);
        } catch (IOException e) {
            System.err.println("Could not load settings file");
            e.printStackTrace();
            return null;
        }

        JSONObject object = new JSONObject(content);
        HashMap<String, Object> settingsMap = new HashMap<String, Object>();
        doRepetiveStuff(object, settingsMap, "");
        return settingsMap;
    }

    private void doRepetiveStuff(JSONObject base, HashMap<String, Object> values, String baseString){
        for(String key : base.keySet()){
            Object node = base.get(key);
            if(node instanceof JSONObject){
                doRepetiveStuff((JSONObject) node, values, baseString + key + ".");
            }else{
                values.put(baseString + key, node);
            }
        }
    }

}
