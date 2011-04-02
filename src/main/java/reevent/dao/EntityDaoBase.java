package reevent.dao;

import reevent.domain.EntityBase;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

public abstract class EntityDaoBase<T extends EntityBase> implements EntityDao<T> {
    protected Class<T> entityClass;

    public EntityDaoBase() {
        entityClass = getSuperTypeToken();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getSuperTypeToken() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        Type entityType = ((ParameterizedType) superclass).getActualTypeArguments()[0];
        if (!(entityType instanceof Class)) {
            throw new RuntimeException("Entity type not a class.");
        }
        return (Class<T>) entityType;
    }

    public EntityDaoBase(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

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
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public void delete(UUID id) {
        T ref = get(id);
        em.remove(ref);
    }

    @Override
    public List<T> findAll(int first, int max) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> q = builder.createQuery(entityClass);
        Root<T> root = q.from(entityClass);
        q.select(root);
        return em.createQuery(q).setFirstResult(first).setMaxResults(max).getResultList();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
