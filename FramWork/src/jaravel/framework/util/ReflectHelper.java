package jaravel.framework.util;

import jaravel.framework.mvc.Model;

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

    public static void setValue(Object object, String field, Object value) {
        try {
            object.getClass().getField(field).set(object, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
