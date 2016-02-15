package jaravel.example.model;

import jaravel.framework.database.builder.WhereClause;
import jaravel.framework.database.schema.Column;
import jaravel.framework.mvc.Model;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Sinius on 13-1-2016.
 */
public class User extends Model {

    @Column(type = "int", size = 50, decimal = 0, nullable = Column.FALSE, primary = Column.TRUE)
    public int id;

    @Column(size = 100)
    public String name;

    @Column(size = 80)
    public String email;

    @Column
    public Date joinedDate;

    public User(){
        super();
    }

    public User(int id) throws IOException {
        super();
        //todo: zorg dat dit makkelijker kan
        this.inflate(new WhereClause<>(this.getColumns()[0], id));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", joinedDate=" + joinedDate +
                '}';
    }
}
