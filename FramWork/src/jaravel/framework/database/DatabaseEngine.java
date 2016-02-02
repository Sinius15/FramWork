package jaravel.framework.database;

import jaravel.framework.database.builder.QueryBuilder;
import jaravel.framework.database.builder.SelectQueryBuilder;
import jaravel.framework.database.types.DatabaseDataTypeProvider;

import java.util.HashMap;

/**
 * Created by Sinius on 13-1-2016.
 */
public abstract class DatabaseEngine {

    public abstract String compile(SelectQueryBuilder builder);

    public abstract HashMap<String, DatabaseDataTypeProvider> getDatabaseDataTypes();

    public String compile(QueryBuilder queryBuilder) {
        if(queryBuilder instanceof  SelectQueryBuilder){
            return this.compile((SelectQueryBuilder) queryBuilder);
        }
        return null;
    }
}
