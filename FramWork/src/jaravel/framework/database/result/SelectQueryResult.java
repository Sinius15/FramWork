package jaravel.framework.database.result;

import jaravel.framework.database.DatabaseRow;

/**
 * Created by Sinius on 15-1-2016.
 */
public class SelectQueryResult extends QueryResult{

    private DatabaseRow[] rows;

    public SelectQueryResult(DatabaseRow[] rows) {
        this.rows = rows;
    }

    public DatabaseRow[] getRows() {
        return rows;
    }
}
