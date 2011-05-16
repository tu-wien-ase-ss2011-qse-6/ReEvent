package reevent.domain;


import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QEntityBase is a Querydsl query type for EntityBase
 */
public class QEntityBase extends EntityPathBase<EntityBase> {

    private static final long serialVersionUID = -1102970121;

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public QEntityBase(BeanPath<? extends EntityBase> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QEntityBase(PathMetadata<?> metadata) {
        super(EntityBase.class, metadata);
    }

}

