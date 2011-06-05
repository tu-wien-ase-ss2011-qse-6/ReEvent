package reevent.web.model;

import org.apache.wicket.model.LoadableDetachableModel;
import reevent.dao.EntityDao;
import reevent.domain.EntityBase;

import java.util.UUID;

public class DaoIdModel<T extends EntityBase> extends LoadableDetachableModel<T> {
    EntityDao<? extends T> dao;
    UUID id;

    public DaoIdModel(EntityDao<? extends T> dao, UUID id) {
        this.dao = dao;
        this.id = id;
    }

    public DaoIdModel(EntityDao<? extends T> dao, UUID id, T value) {
        super(value);
        this.dao = dao;
        this.id = id;
    }

    @Override
    protected T load() {
        return dao.load(id);
    }
}
