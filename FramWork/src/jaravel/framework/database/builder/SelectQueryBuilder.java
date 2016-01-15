package jaravel.framework.database.builder;

import jaravel.framework.Jaravel;
import jaravel.framework.database.connectors.DatabaseConnector;
import jaravel.framework.database.result.QueryResult;
import jaravel.framework.database.scheme.DatabaseColumnScheme;
import jaravel.framework.database.scheme.DatabaseTableScheme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sinius on 13-1-2016.
 */
public class SelectQueryBuilder extends QueryBuilder {

    protected DatabaseColumnScheme[] selectColumns;
    protected ArrayList<WhereClause<?>> whereClauses;

    public SelectQueryBuilder(DatabaseTableScheme table, DatabaseColumnScheme[] selectColumns) {
        super(table);
        this.selectColumns = selectColumns;
    }

    public DatabaseColumnScheme[] getSelectColumns() {
        return selectColumns;
    }

    public void where(WhereClause<?> clause) {
        whereClauses.add(clause);
    }

    public void whereAll(WhereClause<?>[] clauses){
        Collections.addAll(whereClauses, clauses);
    }

    @Override
    public QueryResult execute() throws IOException {
        DatabaseConnector connection = Jaravel.getDatabaseConnection();

        return connection.executePreparedSelectStatement(this, new String[0]);
    }
}
