package jaravel.framework.database.result;

import jaravel.framework.mvc.Model;

/**
 * Created by Sinius on 15-1-2016.
 */
public class SelectQueryResult extends QueryResult{

    private Model[] rows;

    public SelectQueryResult(Model[] rows) {
        this.rows = rows;
    }

    public Model[] getRows() {
        return rows;
    }
}
