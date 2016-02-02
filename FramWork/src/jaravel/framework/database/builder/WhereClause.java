package jaravel.framework.database.builder;

/**
 * Created by Sinius on 15-1-2016.
 */
public class WhereClause<T> {

    public String column;

    public T value;

    public WhereClause(String column, T value) {
        this.column = column;
        this.value = value;
    }
}
