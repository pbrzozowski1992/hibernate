package pl.sdaacademy.api;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sdaacademy.service.HibernateService;

import java.util.List;

public class DBExecutor implements Executor {

    private final HibernateService hibernateService;

    public DBExecutor(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public void execute(Action action) {
        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            action.onExecute(session);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            hibernateService.closeSession();
        }
    }

    @Override
    public List executeQuery(Action action) {
        return null;
    }


    private Session getSession() {
        hibernateService.openSession();
        return hibernateService.getSession();
    }
}
