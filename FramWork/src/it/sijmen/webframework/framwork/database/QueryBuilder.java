package it.sijmen.webframework.framwork.database;

/**
 * Created by Sinius on 13-1-2016.
 */
public abstract class QueryBuilder {

    private String table;

    public QueryBuilder(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }

}
