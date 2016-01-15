package jaravel.example.model;

import jaravel.example.scheme.UsersTable;
import jaravel.framework.database.DatabaseRow;
import jaravel.framework.database.scheme.DatabaseTableScheme;
import jaravel.framework.mvc.Model;

import java.io.IOException;

/**
 * Created by Sinius on 13-1-2016.
 */
public class UserModel extends Model {

    private UsersTable table = new UsersTable();

//    public int id;
    public String name;
//    public String email;
//    public Date joinedDate;

    public UserModel(int id) {
        try {
            DatabaseRow row = this.findModel();
            this.fillMeWithRow(row);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DatabaseTableScheme getTable() {
        return table;
    }
}
