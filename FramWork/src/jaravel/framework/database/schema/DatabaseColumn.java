package jaravel.framework.database.schema;

import jaravel.framework.database.types.DatabaseDataType;
import jaravel.framework.mvc.Model;

/**
 * Created by Sijmen on 1-2-2016.
 */
public class DatabaseColumn {

    private Model parentModel;

    private String name;

    private DatabaseDataType dataType;

    private boolean primary;
    private boolean nullable;

    public DatabaseColumn(Model parentModel){
        this.parentModel = parentModel;
    }

    public DatabaseColumn(String name, DatabaseDataType dataType, boolean primary, boolean nullable) {
        this.name = name;
        this.dataType = dataType;
        this.primary = primary;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DatabaseDataType getDataType() {
        return dataType;
    }

    public void setDataType(DatabaseDataType dataType) {
        this.dataType = dataType;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public Model getParentModel() {
        return parentModel;
    }
}
