package jaravel.framework.database.types;

import jaravel.framework.database.schema.Column;

import java.util.HashMap;

/**
 * Created by Sijmen on 1-2-2016.
 */
public abstract class DatabaseDataTypeProviderManager {

    private static HashMap<String, DatabaseDataTypeProvider> providers = new HashMap<>();

    private static HashMap<String, Default> defaults = new HashMap<>();

    public static void addProvider(String key, DatabaseDataTypeProvider item){
        providers.put(key, item);
    }

    public static void setDefault(String key, int size, int decimal){
        defaults.put(key, new Default(size, decimal));
    }

    public static int getDefaultSize(String key){
        if(!defaults.containsKey(key))
            return Integer.MIN_VALUE;
        return defaults.get(key).getSize();
    }

    public static int getDefaultDecimal(String key){
        if(!defaults.containsKey(key))
            return Integer.MIN_VALUE;
        return defaults.get(key).getDecimal();
    }

    public static int getOrDefaultSize(String key, int originalSize){
        if(originalSize != Column.AUTOMATIC)
            return originalSize;
        if(defaults.containsKey(key))
            return defaults.get(key).getSize();
        throw new IllegalArgumentException("Tryed to use size for datatype " + key + ", but no default value was present.");
    }

    public static int getOrDefaultDecimal(String key, int originalDecimal){
        if(originalDecimal != Column.AUTOMATIC)
            return originalDecimal;
        if(defaults.containsKey(key))
            return defaults.get(key).getDecimal();
        throw new IllegalArgumentException("Tryed to use decimals for datatype " + key + ", but no default value was present.");
    }

    public static DatabaseDataType getDatabaseDataType(String name, Column options){
        for(String key : providers.keySet()){
            if(name.equals(key)){
                return providers.get(key).getType(new DatabaseDataTypeInitializer(
                        name,
                        getOrDefaultSize(name, options.size()),
                        getOrDefaultDecimal(name, options.decimal()),
                        options.customValues()
                ));
            }
        }
        return null;
    }

    static class Default{
        private int size, decimal;

        public Default(int size, int decimal) {
            this.size = size;
            this.decimal = decimal;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getDecimal() {
            return decimal;
        }

        public void setDecimal(int decimal) {
            this.decimal = decimal;
        }
    }

}
