package jaravel.framework.database;

/**
 * Created by Sinius on 13-1-2016.
 */
public class SelectQueryBuilder extends QueryBuilder{

    protected DatabaseColumn[] selectColumns;

    public SelectQueryBuilder(String table, DatabaseColumn[] selectColumns) {
        super(table);
        this.selectColumns = selectColumns;
    }

    public DatabaseColumn[] getSelectColumns() {
        return selectColumns;
    }
}
