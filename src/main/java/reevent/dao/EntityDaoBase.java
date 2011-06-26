package reevent.dao;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.path.EntityPathBase;
import org.springframework.transaction.annotation.Transactional;
import reevent.domain.EntityBase;
import reevent.util.SuperTypeTokenUtil;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Transactional
public abstract class EntityDaoBase<T extends EntityBase> implements EntityDao<T> {
    protected Class<T> entityClass;
    protected EntityPathBase<T> root;

    public EntityDaoBase() {
        entityClass = SuperTypeTokenUtil.findToken(this.getClass(), 0);
    }

    public EntityDaoBase(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @PostConstruct
    void initQueryDsl() {
        StringBuffer entityName = new StringBuffer(entityClass.getSimpleName());
        entityName.setCharAt(0, Character.toLowerCase(entityName.charAt(0)));
        root = new EntityPathBase<T>(entityClass, entityName.toString());
    }

    @PersistenceContext
    EntityManager em;

    @Override
    public T load(UUID id) {
        return em.find(entityClass, id);
    }

    @Override
    public T get(UUID id) {
        return em.getReference(entityClass, id);
    }

    @Override
    public <U extends T> U update(U entity) {
        return em.merge(entity);
    }

    @Override
    public <U extends T>  U save(U entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public void delete(UUID id) {
        T ref = get(id);
        em.remove(ref);
    }

    @Override
    public void refresh(T entity) {
        em.refresh(entity);
    }

    protected JPQLQuery query() {
        return new JPAQuery(em);
    }

    protected JPQLQuery query(EntityPath<?> from) {
        return query().from(from);
    }

    protected JPQLQuery query(EntityPath<?> from, Integer offset, Integer limit) {
        JPQLQuery q = query(from);
        q = offsetLimit(q, offset, limit);
        return q;
    }

    protected JPQLQuery offsetLimit(JPQLQuery q, Integer first, Integer max) {
        if (first != null) {
            q = q.offset(first);
        }
        if (max != null) {
            q = q.limit(max);
        }
        return q;
    }

    @Override
    public List<T> findAll(Integer first, Integer max) {
        return query(root, first, max).list(root);
    }

    @Override
    public List<T> findAll() {
        return query(root).list(root);
    }

}
