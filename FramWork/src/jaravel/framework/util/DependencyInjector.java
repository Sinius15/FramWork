package jaravel.framework.util;

import jaravel.framework.mvc.Model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by Sijmen on 1-2-2016.
 */
public class DependencyInjector {

    @Deprecated
    public static Model getEmptyModel(Class<? extends Model> modelClass){
        try {
            return modelClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("models should have always an empty constructor.");
            e.printStackTrace();
        }
        return null;
    }

    private HashMap<Class<?>, Object> parameterMap;

    public DependencyInjector() {
        parameterMap = new HashMap<>();
    }

    public void putValue(Class<?> parameterClass, Object value){
        parameterMap.put(parameterClass, value);
    }

    //todo: add caching
    public Object getObjectMagicly(Class claz){
        try {
            for(Constructor<?> constructor : claz.getConstructors()){
                Object[] parameters = getConstructorParameters(constructor);
                if(parameters != null) {
                    return constructor.newInstance(parameters);
                }
            }
        } catch (Exception e) {
            System.out.println("Could not initialize class " + claz.getName() + ". Probably because it does not have an empty constructor.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if all constructor parameter types are assignable from
     * one of the classes in the {@link #parameterMap} map.
     * @param constructor The constructor to check
     * @return wether or not its a valid constructor
     */
    private Object[] getConstructorParameters(Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] fields = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            Class<?> foundClaz = this.containsMapAssignableFrom(parameterType);
            if (foundClaz == null)
                return null;
            fields[i] = parameterMap.get(foundClaz);
        }
        return fields;
    }

    /**
     * Tries to find a key in the {@link #parameterMap}
     * @param claz The class to check against.
     * @return null if not found or the class key
     */
    private Class<?> containsMapAssignableFrom(Class<?> claz){
        for(Class<?> mappedClaz : parameterMap.keySet()){
            if(claz.isAssignableFrom(mappedClaz))
                return mappedClaz;
        }
        return null;
    }

    /**
     * Set a value of a field in an object.
     * @param object The object to change the field value in.
     * @param field The name of the field to set.
     * @param value The value to set the field of the object of.
     */
    public static void setValue(Object object, String field, Object value) {
        try {
            object.getClass().getDeclaredField(field).set(object, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a value of a field in an object.
     * @param object The object to get the field value from
     * @param field The name of the field to get
     * @return The value of the field in the object or null if not found.
     */
    public static Object getValue(Object object, String field) {
        try {
            return object.getClass().getDeclaredField(field).get(object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
