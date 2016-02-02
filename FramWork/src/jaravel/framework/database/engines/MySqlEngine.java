package jaravel.framework.database.engines;

import jaravel.framework.database.DatabaseEngine;
import jaravel.framework.database.builder.SelectQueryBuilder;
import jaravel.framework.database.types.DatabaseDataType;
import jaravel.framework.database.types.DatabaseDataTypeInitializer;
import jaravel.framework.database.types.DatabaseDataTypeProvider;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Created by Sinius on 13-1-2016.
 */
public class MySqlEngine extends DatabaseEngine{

    private String leftEscape = "`";
    private String rightEscape = "`";

    @Override
    public String compile(SelectQueryBuilder q) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");

        StringJoiner joiner = new StringJoiner(",");

        for(String column : q.getColumns())
            joiner.add(leftEscape + column + rightEscape);

        builder.append(joiner);

        builder.append(" FROM ");
        builder.append(leftEscape);
        builder.append(q.getModel().getTableName());
        builder.append(rightEscape);

        return builder.toString();
    }

    @Override
    public HashMap<String, DatabaseDataTypeProvider> getDatabaseDataTypes() {
        HashMap<String, DatabaseDataTypeProvider> out = new HashMap<>();

        out.put("boolean", this::booleanType);
        out.put("char", this::charType);
        out.put("string", this::stringType);
        out.put("date", this::dateType);
        out.put("datetime", this::datetimeType);
        out.put("double", this::doubleType);
        out.put("float", this::floatType);
        out.put("enum", this::enumType);
        out.put("int", this::integerType);

        return out;
    }

    public DatabaseDataType booleanType(DatabaseDataTypeInitializer column) {
        return new DatabaseDataType("TINYINT", column.size);
    }

    public DatabaseDataType charType(DatabaseDataTypeInitializer column) {
        return new DatabaseDataType("CHAR", column.size);
    }

    public DatabaseDataType stringType(DatabaseDataTypeInitializer column) {
        return new DatabaseDataType("TEXT", column.size);
    }

    public DatabaseDataType dateType(DatabaseDataTypeInitializer column) {
        return new DatabaseDataType("DATE");
    }

    public DatabaseDataType datetimeType(DatabaseDataTypeInitializer column) {
        return new DatabaseDataType("DATETIME");
    }

    public DatabaseDataType doubleType(DatabaseDataTypeInitializer column) {
        return new DatabaseDataType("DECIMAL", column.size, column.decimal);
    }

    public DatabaseDataType floatType(DatabaseDataTypeInitializer column) {
        return new DatabaseDataType("FLOAT", column.size, column.decimal);
    }

    public DatabaseDataType enumType(DatabaseDataTypeInitializer column) {
        return new DatabaseDataType("ENUM", column.customValues);
    }

    public DatabaseDataType integerType(DatabaseDataTypeInitializer column) {
        return new DatabaseDataType("INT", column.size);
    }

}
