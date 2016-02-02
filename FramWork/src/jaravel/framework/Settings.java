package jaravel.framework;

import jaravel.framework.database.DatabaseEngine;
import jaravel.framework.database.connectors.DatabaseConnector;
import jaravel.framework.database.connectors.JDBCConnector;
import jaravel.framework.database.engines.MySqlEngine;

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

    public String database_jdbc_driver = "com.mysql.jdbc.Driver";

    /**
     * The class to use for the database engine. Default
     * database engine is MySqlEngine.
     */
    public Class<? extends DatabaseEngine> database_engine = MySqlEngine.class;

}
