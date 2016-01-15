package jaravel.framework.database.builder;

import jaravel.framework.database.result.QueryResult;
import jaravel.framework.database.scheme.DatabaseTableScheme;

import java.io.IOException;

/**
 * Created by Sinius on 13-1-2016.
 */
public abstract class QueryBuilder {

    private DatabaseTableScheme table;

    public QueryBuilder(DatabaseTableScheme table) {
        this.table = table;
    }

    public DatabaseTableScheme getTable() {
        return table;
    }

    public abstract QueryResult execute() throws IOException;
}
