package jaravel.framework.database.connectors;

import jaravel.framework.Jaravel;
import jaravel.framework.database.DatabaseCell;
import jaravel.framework.database.DatabaseRow;
import jaravel.framework.database.builder.SelectQueryBuilder;
import jaravel.framework.database.result.SelectQueryResult;
import jaravel.framework.database.scheme.DatabaseColumnScheme;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Sinius on 13-1-2016.
 */
public class JDBCConnector extends DatabaseConnector {

    private Connection connection;

    public JDBCConnector(String driver) throws IOException {
        super();
        if(driver == null)
            throw new IllegalArgumentException("Driver cannot be null");
        try{
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IOException("Could not initialize driver "+driver+" : " + e.getMessage());
        }
    }

    @Override
    public void connect(String JDBCStr) throws IOException{
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

        DatabaseColumnScheme[] cols = query.getSelectColumns();

        ArrayList<DatabaseRow> rows = new ArrayList<>();
        query.getSelectColumns();
        try {
            while(set.next()){
                DatabaseCell<?>[] cells = new DatabaseCell[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    DatabaseColumnScheme<?> col = cols[i];
                    switch (col.getTypeName()) {
                        case "java.lang.String":
                            cells[i] = new DatabaseCell<>(
                                    query.getTable(),
                                    (DatabaseColumnScheme<String>)col,
                                    set.getString(col.getName())
                            );
                            break;
                    }
                }
                rows.add(new DatabaseRow(query.getTable(), cells));
            }
        } catch (SQLException e) {
            throw new IOException("Could not fetch result from QueryResult: " + e.getMessage());
        }

        return new SelectQueryResult(rows.toArray(new DatabaseRow[rows.size()]));
    }

}
