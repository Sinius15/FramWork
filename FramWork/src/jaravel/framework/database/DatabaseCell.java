package jaravel.framework.database;

import jaravel.framework.database.scheme.DatabaseColumnScheme;
import jaravel.framework.database.scheme.DatabaseTableScheme;

/**
 * Created by Sinius on 15-1-2016.
 */
public class DatabaseCell<T> {

    private T originalValue;
    private T value;

    private DatabaseColumnScheme<T> column;

    private DatabaseTableScheme table;

    public DatabaseCell(DatabaseTableScheme table, DatabaseColumnScheme<T> column, T value) {
        this.value = value;
        this.column = column;
        this.table = table;
        originalValue = value;
    }

    public T getValue() {
        return value;
    }

    public T getOriginalValue() {
        return originalValue;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DatabaseColumnScheme getColumn() {
        return column;
    }

    public DatabaseTableScheme getTable() {
        return table;
    }
}
