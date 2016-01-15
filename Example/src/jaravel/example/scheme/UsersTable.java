package jaravel.example.scheme;

import jaravel.framework.database.scheme.DatabaseColumnScheme;
import jaravel.framework.database.scheme.DatabaseTableScheme;

import java.util.Date;

/**
 * Created by Sinius on 15-1-2016.
 */
public class UsersTable extends DatabaseTableScheme {

//    public static final DatabaseColumnScheme<Integer> id = new DatabaseColumnScheme<>("id", false, true);
    public static final DatabaseColumnScheme<String> name = new DatabaseColumnScheme<>("name", true);
//    public static final DatabaseColumnScheme<String> email = new DatabaseColumnScheme<>("email");
//    public static final DatabaseColumnScheme<Date> joinedDate = new DatabaseColumnScheme<>("joined_date");

    private static final String tableName = "users";

    private static UsersTable instance;

    public UsersTable() {
        super(tableName,
                new DatabaseColumnScheme[]{
//                    id,
                    name,
//                    email,
//                    joinedDate
        });
        instance = this;
    }

}
