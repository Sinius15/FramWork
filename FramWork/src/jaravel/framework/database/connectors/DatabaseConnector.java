package jaravel.framework.database.connectors;

import jaravel.framework.database.builder.SelectQueryBuilder;
import jaravel.framework.database.result.SelectQueryResult;

import java.io.IOException;

/**
 * Created by Sinius on 13-1-2016.
 */
public abstract class DatabaseConnector {

    public DatabaseConnector() {

    }

    public abstract void connect(String JDBCStr) throws IOException;

    public abstract SelectQueryResult executePreparedSelectStatement(SelectQueryBuilder query, String[] arguments) throws IOException;

}
