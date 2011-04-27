package reevent.dao;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;
import org.springframework.transaction.annotation.Transactional;
import reevent.domain.EntityBase;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Transactional
public abstract class EntityDaoBase<T extends EntityBase> implements EntityDao<T> {
    protected Class<T> entityClass;
    protected EntityPathBase<T> root;

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

    protected JPQLQuery query() {
        return new JPAQuery(em);
    }
    
    @Override
    public List<T> findAll(int first, int max) {
        return query().from(root).offset(first).limit(max).list(root);
    }

}
