package jaravel.framework.database.scheme;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Sinius on 13-1-2016.
 */
public class DatabaseColumnScheme<T> {

    private String name;
    private boolean nullable = true;
    private boolean primary = false;

    public DatabaseColumnScheme(String name) {
        this.name = name;
    }

    public DatabaseColumnScheme(String name, boolean nullable) {
        this.name = name;
        this.nullable = nullable;
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

    public Class<T> getType() {
        try {
            String className = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            return (Class<T>) Class.forName(className);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not find generic class type: " + e.getMessage());
        }
    }

    public String getTypeName() {
        Type type = getClass().getGenericSuperclass();
        System.out.println(getClass());

        Class<?> c = ((ParameterizedType) type).getActualTypeArguments()[0].getClass();

        try {
            return c.getName();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not find generic class name: " + e.getMessage());
        }
    }
}