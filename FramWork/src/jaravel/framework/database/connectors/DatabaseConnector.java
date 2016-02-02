package jaravel.framework.database.connectors;

import jaravel.framework.Settings;
import jaravel.framework.database.builder.SelectQueryBuilder;
import jaravel.framework.database.result.SelectQueryResult;

import java.io.IOException;

/**
 * Created by Sinius on 13-1-2016.
 */
public abstract class DatabaseConnector {

    protected Settings settings;

    public DatabaseConnector(Settings settings) {
        this.settings = settings;
    }

    public abstract void connect() throws IOException;

    public abstract SelectQueryResult executePreparedSelectStatement(SelectQueryBuilder query, String[] arguments) throws IOException;

}
