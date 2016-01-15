package jaravel.framework.database;

import jaravel.framework.database.scheme.DatabaseTableScheme;

/**
 * Created by Sinius on 15-1-2016.
 */
public class DatabaseRow {

    private DatabaseCell<?>[] cells;

    private DatabaseTableScheme table;

    public DatabaseRow(DatabaseTableScheme table, DatabaseCell<?>[] cells) {
        this.cells = cells;
        this.table = table;
    }

    public DatabaseCell<?>[] getCells() {
        return cells;
    }

    public DatabaseTableScheme getTable() {
        return table;
    }
}
