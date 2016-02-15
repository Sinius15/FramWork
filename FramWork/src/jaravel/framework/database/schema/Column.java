package jaravel.framework.database.schema;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Sijmen on 1-2-2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    String AUTOMATICS = "";

    //todo: make enum
    int AUTOMATIC = Integer.MIN_VALUE;
    int TRUE = 1;
    int FALSE = 2;

    /**
     * Database column name
     * @return The name of the column in the phisical database.
     */
    String name() default AUTOMATICS;

    /**
     * The name of the datatype of the colomn
     * @return The name of the datatype.
     */
    String type() default AUTOMATICS;

    /**
     * The size of the datatype of the column. This option
     * is ignored for some datatypes.
     * @return The size of this datatype.
     */
    int size() default AUTOMATIC;

    /**
     * This is the number of digits to the right of the decimal
     * point. For some datatype this option is ignored.
     * @return The decimal point for this datatype.
     */
    int decimal() default AUTOMATIC;

    /**
     * This tells if this column may contain null values.
     * @return Whether or not this column may contain null values
     */
    int nullable() default AUTOMATIC;

    /**
     * This tells if this column is part of the primary key.
     * @return Whether or not this column is part of the primary key.
     */
    int primary() default AUTOMATIC;

    /**
     * This option may contain raw options that are parsed to the
     * database engine. This can for example be used for enums.
     * @return Custom options.
     */
    String[] customValues() default {};
}
