package jaravel.framework.database.types;

/**
 * Created by Sijmen on 1-2-2016.
 */
public class DatabaseDataTypeInitializer {

    public String type;
    public int size, decimal;
    public String[] customValues;

    public DatabaseDataTypeInitializer(String type, int size, int decimal, String[] customValues) {
        this.type = type;
        this.size = size;
        this.decimal = decimal;
        this.customValues = customValues;
    }


}
