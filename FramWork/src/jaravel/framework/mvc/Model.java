package jaravel.framework.mvc;

import jaravel.framework.database.schema.DatabaseColumn;
import jaravel.framework.database.schema.ModelFactory;

/**
 * Created by Sijmen on 12-1-2016.
 */
public abstract class Model {

    protected String tableNamee;

    protected DatabaseColumn[] columns;

    public Model(){
        ModelFactory factory = new ModelFactory();
        this.columns = factory.makeColumns(this);
        if(tableName == null)
            tableName = factory.makeTableName(this);
    }


    public void delete(){
        //todo
    }

    public void save(){
        //todo
    }

    public String getTableName() {
        return tableName;
    }

//    public DatabaseRow findModel(WhereClause... where) throws IOException {
//        SelectQueryBuilder builder = new SelectQueryBuilder(getTable(), getTable().getColumns());
//        builder.whereAll(where);
//        SelectQueryResult result = (SelectQueryResult) builder.execute();
//
//        DatabaseRow[] rows = result.getRows();
//
//        if(rows.length > 1)
//            throw new IOException("Found multiple results matching where clause");
//        if(rows.length < 1)
//            throw new IOException("No matching results found.");
//        return rows[0];
//    }


//    public void fillMeWithRow(DatabaseRow row) throws IOException {
//        if(!row.getTable().equals(this.getTable()))
//            throw new IOException("Tables do not match.");
//
//        for(DatabaseCell<?> cell : row.getCells()){
//            Field field;
//            try {
//                field = this.getClass().getField(cell.getColumn().getName());
//            } catch (NoSuchFieldException e) {
//                throw new IOException("Could not map database scheme column to model field: " + e.getMessage());
//            }
//            try {
//                field.set(this, cell.getValue());
//            } catch (IllegalAccessException e) {
//                throw new IOException("Could not fill model field '"+field.getName()+"'with DatabaseCell value: " + e.getMessage());
//            }
//        }
//    }
}
