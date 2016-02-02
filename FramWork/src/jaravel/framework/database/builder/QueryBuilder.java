package jaravel.framework.database.builder;

import jaravel.framework.database.result.QueryResult;
import jaravel.framework.mvc.Model;

import java.io.IOException;

/**
 * Created by Sinius on 13-1-2016.
 */
public abstract class QueryBuilder {

    private Model model;

    public QueryBuilder(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public abstract QueryResult execute() throws IOException;
}
