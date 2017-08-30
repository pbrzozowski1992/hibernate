package pl.sdaacademy.table;

import org.hibernate.Session;
import pl.sdaacademy.api.ActionAdapter;
import pl.sdaacademy.api.Executor;
import pl.sdaacademy.model.Author;

import java.util.List;

public class AuthorsManager extends BaseManager<Author> {

    public AuthorsManager(Executor executor) {
        super(executor);
    }

    @Override
    public void add(Author object) {
        executor.execute(new ActionAdapter() {
            @Override
            public void onExecute(Session session) {
                session.save(object);
            }
        });
    }

    @Override
    public void update(Author object) {
    }

    @Override
    public void delete(Author object) {

    }

    @Override
    public List<Author> getList() {
        return null;
    }

    @Override
    public String getSelectQuery() {
        return String.format("FROM %s", Author.TABLE_NAME);
    }
}
