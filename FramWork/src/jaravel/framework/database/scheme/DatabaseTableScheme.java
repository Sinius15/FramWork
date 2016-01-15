package jaravel.framework.database.scheme;

/**
 * Created by Sinius on 15-1-2016.
 */
public abstract class DatabaseTableScheme {

    private String name;

    private DatabaseColumnScheme<?>[] columns;

    public DatabaseTableScheme(String name, DatabaseColumnScheme<?>[] columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public DatabaseColumnScheme<?>[] getColumns() {
        return columns;
    }
}
