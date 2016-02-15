package jaravel.framework.mvc;

import jaravel.framework.database.builder.SelectQueryBuilder;
import jaravel.framework.database.builder.WhereClause;
import jaravel.framework.database.result.SelectQueryResult;
import jaravel.framework.database.schema.DatabaseColumn;
import jaravel.framework.database.schema.ModelFactory;
import jaravel.framework.util.DependencyInjector;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Sijmen on 12-1-2016.
 */
public abstract class Model {

    protected String tableName;

    private DatabaseColumn[] columns;

    public Model() {
        ModelFactory factory = new ModelFactory();
        this.columns = factory.makeColumns(this);
        if (tableName == null)
            tableName = factory.makeTableName(this);
    }


    public void delete() {
        //todo
    }

    public void save() {
        //todo
    }

    public String getTableName() {
        return tableName;
    }

    public DatabaseColumn[] getColumns() {
        return columns;
    }

    public String[] getColumnNames() {
        String[] out = new String[getColumns().length];
        for (int i = 0; i < getColumns().length; i++) {
            DatabaseColumn col = getColumns()[i];
            out[i] = col.getName();
        }
        return out;
    }

    public void inflate(WhereClause<?>... where) throws IOException {
        SelectQueryBuilder builder = new SelectQueryBuilder(this, this.getColumnNames());
        builder.whereAll(where);
        SelectQueryResult result = (SelectQueryResult) builder.execute();

        Model[] rows = result.getRows();
        notEmpty(rows.length);
        Model row = rows[0];

        for(DatabaseColumn column : getColumns()){
            DependencyInjector.setValue(this, column.getName(), DependencyInjector.getValue(row, column.getName()));
        }
    }

    protected void notEmpty(int length) throws IOException {
        if (length > 1)
            throw new IOException("Found to many resulsts.");
        if (length < 1)
            throw new IOException("No matching results found.");
    };
}