package jaravel.framework.util;

import jaravel.framework.mvc.Model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Sijmen on 1-2-2016.
 */
public class ReflectHelper {

    public static Model getEmptyModel(Class<? extends Model> modelClass){
        try {
            return modelClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("models should have always an empty constructor.");
            e.printStackTrace();
        }
        return null;
    }

    public static Object getEmptyObject(Class obj){
        try {
            return obj.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Could not initialize class " + obj.getName() + ". Probably because it does not have an empty constructor.");
            e.printStackTrace();
        }
        return null;
    }

    public static Object getEmptyObject(Class obj, Class<?>[] classes, Object[] args){
        try {
            Constructor<?> constructor = obj.getConstructor(classes);
            return constructor.newInstance(args);
        } catch (Exception e) {
            System.out.println("Could not initialize class " + obj.getName() + ". Probably because it does not have the expected arguments.");
            e.printStackTrace();
        }
        return null;
    }

    public static void setValue(Object object, String field, Object value) {
        try {
            object.getClass().getField(field).set(object, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
