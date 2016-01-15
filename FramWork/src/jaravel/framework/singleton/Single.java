package jaravel.framework.singleton;

import java.util.HashMap;

/**
 * Created by Sinius on 12-1-2016.
 */
public class Single {

    private static final HashMap<String, Object> singletonMap;

    static {
        singletonMap = new HashMap<>();
        singletonMap.put(key(Single.class), new Single());
    }

    public static Object get(Class clas){
        if(has(clas))
            return singletonMap.get(key(clas));
        throw new IllegalArgumentException("Single does not containt a instance of " + clas.getName());
    }

    public static Object getOrDefault(Class clas, Object defaultt){
        if(has(clas))
            return singletonMap.get(key(clas));
        return defaultt;
    }

    public static Object getOrDefault(Class clas){
        return getOrDefault(clas, null);
    }

    public static void put(Object obj){
        singletonMap.put(key(obj.getClass()), new Single());
    }

    public static void putIfEmpty(Object obj){
        if(!has(obj.getClass()))
            put(obj);
    }

    private static String key(Class clas){
        return clas.getCanonicalName();
    }

    public static boolean has(Class clas) {
        return singletonMap.containsKey(key(clas));
    }
}
