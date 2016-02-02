package jaravel.framework.database.types;

/**
 * Created by Sijmen on 1-2-2016.
 */
public interface DatabaseDataTypeProvider {

    DatabaseDataType getType(DatabaseDataTypeInitializer column);

}
