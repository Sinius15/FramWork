package jaravel.framework.database.schema;

import jaravel.framework.database.types.DatabaseDataTypeProviderManager;
import jaravel.framework.mvc.Model;

import java.lang.reflect.Field;

/**
 * Created by Sijmen on 1-2-2016.
 */
public class ColumnParser {

    public static boolean DEFAULT_NULLABLE;
    public static boolean DEFAULT_PRIMARY;
    public static String DEFAULT_DATA_TYPE_NAME = "string";

    private Column annotation;
    private Field field;
    private Model model;

    public ColumnParser(Model model, Field field, Column annotation) {
        this.annotation = annotation;
        this.field = field;
        this.model = model;
    }

    public DatabaseColumn parseToColumn() {
        DatabaseColumn column = new DatabaseColumn(model);

        column.setName(getColumnName());
        column.setNullable(getNullable());
        column.setPrimary(getPrimary());
        column.setDataType(
                DatabaseDataTypeProviderManager.getDatabaseDataType(
                        getDataTypeName(), annotation
                ));

        return column;
    }

    private String getColumnName(){
        return annotation.name().equals(Column.AUTOMATICS)
                ? field.getName() : annotation.name();
    }

    protected boolean getNullable(){
        if(annotation.nullable() == Column.AUTOMATIC)
            return DEFAULT_NULLABLE;
        return annotation.nullable() == Column.TRUE;
    }

    protected boolean getPrimary(){
        if(annotation.primary() == Column.AUTOMATIC)
            return DEFAULT_PRIMARY;
        return annotation.primary() == Column.TRUE;
    }

    public String getDataTypeName() {
        return annotation.type().equals(Column.AUTOMATICS)
                ? DEFAULT_DATA_TYPE_NAME : annotation.type();
    }
}
