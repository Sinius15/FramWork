package jaravel.framework.database.connectors;

import jaravel.framework.Jaravel;
import jaravel.framework.Settings;
import jaravel.framework.database.builder.SelectQueryBuilder;
import jaravel.framework.database.result.SelectQueryResult;
import jaravel.framework.mvc.Model;
import jaravel.framework.util.ReflectHelper;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Sinius on 13-1-2016.
 */
public class JDBCConnector extends DatabaseConnector {

    private Connection connection;

    public JDBCConnector(Settings settings) throws IOException {
        super(settings);
        String driver = settings.database_jdbc_driver;
        if(driver == null)
            throw new IllegalArgumentException("settings.database_jdbc_driver cannot be null.");
        try{
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IOException("Could not initialize driver "+driver+" : " + e.getMessage());
        }
    }

    @Override
    public void connect() throws IOException{
        String JDBCStr = settings.database_jdbc_connection;
        if(JDBCStr == null)
            throw new IllegalArgumentException("settings.database_jdbc_connection cannot be null");
        try {
            connection = DriverManager.getConnection(JDBCStr);
        } catch (SQLException e) {
            throw new IOException("Could not connect to database: " + e.getMessage());
        }
    }

    @Override
    public SelectQueryResult executePreparedSelectStatement(SelectQueryBuilder query, String[] arguments) throws IOException {
        String queryStr = Jaravel.getDatabaseEngine().compile(query);

        PreparedStatement state;
        try {
            state = connection.prepareStatement(queryStr);
        } catch (SQLException e) {
            throw new IOException("Could not create statement: " + e.getMessage());
        }
        try {
            for (int i = 0; i < arguments.length; i++) {
                state.setString(i, arguments[i]);
            }
        }catch (SQLException e){
            throw new IOException("Could not add prepared statements: " + e.getMessage());
        }
        ResultSet set;
        try {
            set = state.executeQuery();
        } catch (SQLException e) {
            throw new IOException("Could not execute query: " + e.getMessage());
        }

        String[] cols = query.getColumns();

        ArrayList<Model> rows = new ArrayList<>();
        try {
            while(set.next()){
                Model model = ReflectHelper.getEmptyModel(query.getModel().getClass());

                for(String col : cols){
                    ReflectHelper.setValue(model, col, set.getObject(col));
                }
                rows.add(model);
            }
        } catch (SQLException e) {
            throw new IOException("Could not fetch result from QueryResult: " + e.getMessage());
        }

        return new SelectQueryResult(rows.toArray(new Model[rows.size()]));
    }

}
