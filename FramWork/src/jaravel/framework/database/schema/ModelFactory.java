package jaravel.framework.database.schema;

import jaravel.framework.mvc.Model;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Sijmen on 1-2-2016.
 */
public class ModelFactory {

    public DatabaseColumn[] makeColumns(Model model) {
        ArrayList<DatabaseColumn> columns = new ArrayList<>();
        for(Field field : model.getClass().getDeclaredFields()){
            if(!field.isAnnotationPresent(Column.class))
                continue;

            Column ann = field.getAnnotation(Column.class);

            ColumnParser parser = new ColumnParser(model, field, ann);

            columns.add(parser.parseToColumn());
        }
        return columns.toArray(new DatabaseColumn[columns.size()]);
    }

    public String makeTableName(Model model) {
        return model.getClass().getSimpleName();
    }
}
