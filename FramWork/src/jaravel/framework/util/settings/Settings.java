package jaravel.framework.util.settings;

import jaravel.framework.Jaravel;
import jaravel.framework.database.DatabaseEngine;
import jaravel.framework.database.connectors.DatabaseConnector;
import jaravel.framework.database.connectors.JDBCConnector;
import jaravel.framework.database.engines.MySqlEngine;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Sijmen on 2-2-2016.
 */
public class Settings {

    /**
     * The class to use for the database connection.
     * Default is the JDBCConnector.
     */
    public Class<? extends DatabaseConnector> database_connector = JDBCConnector.class;

    /**
     * IF you use the JDBCConnector as database_connector,
     * than this is the place where you put the connection
     * string. Default is "jdbc:mysql://localhost:3306/"
     */
    public String database_jdbc_connection = "jdbc:mysql://localhost:3306/";

    /**
     * If you use the JDBCConnecor as database_connector,
     * than this is the place where you chose the database
     * connector. Default is com.mysql.jdbc.Driver
     */
    public String database_jdbc_driver = "com.mysql.jdbc.Driver"; //todo: make class?

    /**
     * The class to use for the database engine. Default
     * database engine is MySqlEngine.
     */
    public Class<? extends DatabaseEngine> database_engine = MySqlEngine.class;

    /**
     * What class to use for loading settings. Default
     * is the JSONSettingsLoader.
     */
    public Class<? extends SettingsLoader> settings_loader = JSONSettingsLoader.class;

    /**
     * Constructor of Settings class.
     * If you overwrite this constructor, make sure the
     * "this.settings" field gets initialized.
     */
    public Settings(File settingsFile){
        this.settings = loadSettings(settingsFile);
    }

    /**
     * The loaded settings.
     */
    protected final HashMap<String, Object> settings;

    /**
     * Get a setting from the settings hashmap.
     * @param key The settings key
     * @return The value loaded from the settings hashmap or null if not found.
     */
    protected Object setting(String key) {
        return this.settings.getOrDefault(key, null);
    }

    /**
     * Load all settings using speccified this.settings_loader.
     * @return the hashmap with the loaded settings.
     */
    protected HashMap<String, Object> loadSettings(File file){
        SettingsLoader loader = (SettingsLoader) Jaravel.dependencyInjector.getObjectMagicly(this.settings_loader);
        if (loader == null)
            throw new IllegalArgumentException("Could not load settings loader class.");
        return loader.loadSettings(file);
    }
}
