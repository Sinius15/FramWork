package jaravel.framework.database.types;

/**
 * Created by Sinius on 15-1-2016.
 */
public class DatabaseDataType {

    private String name;
    private Object[] args;

    public DatabaseDataType(String name, Object... args){
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public Object[] getArgs() {
        return args;
    }

}
