package jaravel.example.model;

import jaravel.framework.database.anns.Column;
import jaravel.framework.mvc.Model;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Sinius on 13-1-2016.
 */
public class UserModel extends Model {

    @Column(type = "int", size = 50, decimal = 0, nullable = Column.TRUE, primary = Column.FALSE)
    public int id;

    @Column(size =100)
    public String name;

    @Column(size =80)
    public String email;

    @Column
    public Date joinedDate;

    public UserModel(int id) {
        super();
    }

    public UserModel(){
        super();
    }
}
