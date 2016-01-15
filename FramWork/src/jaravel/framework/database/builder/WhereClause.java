package jaravel.framework.database.builder;

import jaravel.framework.database.scheme.DatabaseColumnScheme;

/**
 * Created by Sinius on 15-1-2016.
 */
public class WhereClause<T> {

    public DatabaseColumnScheme<T> column;

    public T value;

    public WhereClause(DatabaseColumnScheme<T> column, T value) {
        this.column = column;
        this.value = value;
    }
}
