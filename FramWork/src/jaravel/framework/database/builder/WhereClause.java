package jaravel.framework.database.builder;

import jaravel.framework.database.schema.DatabaseColumn;

/**
 * Created by Sinius on 15-1-2016.
 */
public class WhereClause<T> {

    public DatabaseColumn column;

    public T value;

    public WhereClause(DatabaseColumn column, T value) {
        this.column = column;
        this.value = value;
    }
}
