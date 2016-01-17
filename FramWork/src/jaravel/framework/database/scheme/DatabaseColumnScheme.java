package jaravel.framework.database.scheme;

/**
 * Created by Sinius on 13-1-2016.
 */
public class DatabaseColumnScheme<T> {

    public T whoAmI;

    private String name;
    private boolean nullable = true;
    private boolean primary = false;

    public DatabaseColumnScheme(String name) {
        this.name = name;
    }

    public DatabaseColumnScheme(String name, boolean nullable, T example) {
        this.name = name;
        this.nullable = nullable;
        this.whoAmI = example;
    }

    public DatabaseColumnScheme(String name, boolean nullable, boolean primary) {
        this.name = name;
        this.nullable = nullable;
        this.primary = primary;
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

    public boolean isPrimary() {
        return primary;
    }

    public String getTypeName() {

        try {
            Class<?> c = this.getClass().getField("whoAmI").getType();
            System.out.println(c.getTypeName());
            return c.getName();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not find generic class name: " + e.getMessage());
        }
    }
}