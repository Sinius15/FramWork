package jaravel.framework.database.engines;

import jaravel.framework.database.scheme.DatabaseColumnScheme;
import jaravel.framework.database.DatabaseEngine;
import jaravel.framework.database.builder.SelectQueryBuilder;

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

        for(DatabaseColumnScheme column : q.getSelectColumns())
            joiner.add(leftEscape + column.getName() + rightEscape);

        builder.append(joiner);

        builder.append(" FROM ");
        builder.append(leftEscape);
        builder.append(q.getTable().getName());
        builder.append(rightEscape);

        return builder.toString();
    }
}
