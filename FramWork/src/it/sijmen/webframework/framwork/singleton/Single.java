package it.sijmen.webframework.framwork.singleton;

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
        if(singletonMap.containsKey(key(clas)))
            return singletonMap.get(key(clas));
        throw new IllegalArgumentException("Single does not containt a instance of " + clas.getName());
    }

    public static Object getOrDefault(Class clas, Object defaultt){
        if(singletonMap.containsKey(key(clas)))
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
        if(!singletonMap.containsKey(key(obj.getClass())))
            put(obj);
    }

    private static String key(Class clas){
        return clas.getCanonicalName();
    }

}
