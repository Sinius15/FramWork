package it.sijmen.webframework.framwork.database.engines;

import it.sijmen.webframework.framwork.database.DatabaseColumn;
import it.sijmen.webframework.framwork.database.DatabaseEngine;
import it.sijmen.webframework.framwork.database.SelectQueryBuilder;

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

        for(DatabaseColumn column : q.getSelectColumns())
            joiner.add(leftEscape + column.getName() + rightEscape);

        builder.append(joiner);

        builder.append(" FROM ");
        builder.append(leftEscape);
        builder.append(q.getTable());
        builder.append(rightEscape);

        return builder.toString();
    }
}
