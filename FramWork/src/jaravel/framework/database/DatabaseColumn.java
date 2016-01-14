package jaravel.framework.database;

/**
 * Created by Sinius on 13-1-2016.
 */
public class DatabaseColumn {

    private String name;
    private boolean nullable = true;

    public DatabaseColumn(String name) {
        this.name = name;
    }

    public DatabaseColumn(String name, boolean nullable) {
        this.name = name;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
