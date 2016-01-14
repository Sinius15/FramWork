package jaravel.framework.mvc;

import jaravel.framework.database.DatabaseColumn;
import jaravel.framework.database.QueryBuilder;
import jaravel.framework.database.SelectQueryBuilder;

import java.util.HashMap;

/**
 * Created by Sijmen on 12-1-2016.
 */
public abstract class Model {

    protected String table;

    protected DatabaseColumn[] columns;

    protected DatabaseColumn[] primaryKeys = new DatabaseColumn[]{
            new DatabaseColumn("id", false)
    };

    protected HashMap<String, String> attributes;
    protected HashMap<String, String> originalValues;

    /**
     * Create a new model.
     */
    public Model(HashMap<String, String> attributes){
        this.attributes = attributes;
    }

    public Model(){

    }

    public QueryBuilder where(HashMap<String, String> attributes){
        return new SelectQueryBuilder(table, this.columns);
    }

    public void save(){
        //todo
    }

    public void delete(){
        //todo
    }

    public String get(String key){
        return attributes.get(key);
    }

    public String get(DatabaseColumn column){
        if(column == null)
            throw new IllegalArgumentException("column can not be null.");
        return attributes.get(column.getName());
    }

    public void set(String key, String value){
        attributes.put(key, value);
    }

    public void set(DatabaseColumn column, String value){
        if(column == null)
            throw new IllegalArgumentException("column can not be null.");
        attributes.put(column.getName(), value);
    }

    public void fill(HashMap<String, String> attributes){
        this.attributes.putAll(attributes);
    }

    public void fillColumns(HashMap<DatabaseColumn, String> attributes){
        for(DatabaseColumn col : attributes.keySet())
            this.attributes.put(col.getName(), attributes.get(col));
    }





}
