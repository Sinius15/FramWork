package jaravel.framework.database;

/**
 * Created by Sinius on 13-1-2016.
 */
public abstract class DatabaseEngine {

    public abstract String compile(SelectQueryBuilder builder);

}
