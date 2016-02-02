package jaravel.framework.database.builder;

import jaravel.framework.Jaravel;
import jaravel.framework.database.connectors.DatabaseConnector;
import jaravel.framework.database.result.QueryResult;
import jaravel.framework.database.schema.DatabaseColumn;
import jaravel.framework.mvc.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sinius on 13-1-2016.
 */
public class SelectQueryBuilder extends QueryBuilder {

    protected String[] columns;
    protected ArrayList<WhereClause<?>> whereClauses;

    public SelectQueryBuilder(Model model, String[] columns) {
        super(model);
        this.columns = columns;
    }

    public String[] getColumns() {
        return columns;
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
