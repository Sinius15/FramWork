package jaravel.framework.mvc;

import jaravel.framework.database.DatabaseCell;
import jaravel.framework.database.DatabaseRow;
import jaravel.framework.database.builder.SelectQueryBuilder;
import jaravel.framework.database.builder.WhereClause;
import jaravel.framework.database.result.SelectQueryResult;
import jaravel.framework.database.scheme.DatabaseTableScheme;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by Sijmen on 12-1-2016.
 */
public abstract class Model {

    public abstract DatabaseTableScheme getTable();

    public DatabaseRow findModel(WhereClause... where) throws IOException {
        SelectQueryBuilder builder = new SelectQueryBuilder(getTable(), getTable().getColumns());
        builder.whereAll(where);
        SelectQueryResult result = (SelectQueryResult) builder.execute();

        DatabaseRow[] rows = result.getRows();

        if(rows.length > 1)
            throw new IOException("Found multiple results matching where clause");
        if(rows.length < 1)
            throw new IOException("No matching results found.");
        return rows[0];
    }

    public void save(){
        //todo
    }

    public void delete(){
        //todo
    }

    public void fillMeWithRow(DatabaseRow row) throws IOException {
        if(!row.getTable().equals(this.getTable()))
            throw new IOException("Tables do not match.");

        for(DatabaseCell<?> cell : row.getCells()){
            Field field;
            try {
                field = this.getClass().getField(cell.getColumn().getName());
            } catch (NoSuchFieldException e) {
                throw new IOException("Could not map database scheme column to model field: " + e.getMessage());
            }
            try {
                field.set(this, cell.getValue());
            } catch (IllegalAccessException e) {
                throw new IOException("Could not fill model field '"+field.getName()+"'with DatabaseCell value: " + e.getMessage());
            }
        }
    }
}
