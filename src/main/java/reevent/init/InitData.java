package reevent.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import reevent.domain.Event;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;


public class InitData {
    @PersistenceContext
    EntityManager em;

    @Autowired
    PlatformTransactionManager txManager;

    @PostConstruct
    public void initData() {
        TransactionTemplate txTemplate = new TransactionTemplate(txManager);

        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                Query q = em.createQuery("select e from Event e");
                q.setFirstResult(0).setMaxResults(1);
                if (!q.getResultList().isEmpty()) {
                    // Already initialised
                    return;
                }
                em.persist(new Event("event 1", new Date()));
                em.persist(new Event("event 2", new Date()));
                em.persist(new Event("event 3", new Date()));
            }
        });
    }
}