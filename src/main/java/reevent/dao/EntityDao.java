package reevent.dao;

import reevent.domain.EntityBase;

import java.util.List;
import java.util.UUID;

public interface EntityDao<T extends EntityBase> {
    /**
     * Load an entity from the database.
     * @param id
     * @return
     */
    T load(UUID id);

    /**
     * Get a reference to an entity in the database.
     * @param id
     * @return
     */
    T get(UUID id);
    <U extends T> U update(U entity);
    <U extends T> U save(U entity);
    void delete(UUID id);
    List<T> findAll(int first, int max);
}
