package reevent.domain.media;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QMediaBase is a Querydsl query type for MediaBase
 */
public class QMediaBase extends EntityPathBase<MediaBase> {

    private static final long serialVersionUID = 1849472904;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QMediaBase mediaBase = new QMediaBase("mediaBase");

    public final reevent.domain.QEntityBase _super = new reevent.domain.QEntityBase(this);

    public final reevent.domain.QUser addedBy;

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QMediaBase(String variable) {
        this(MediaBase.class, forVariable(variable), INITS);
    }

    public QMediaBase(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMediaBase(PathMetadata<?> metadata, PathInits inits) {
        this(MediaBase.class, metadata, inits);
    }

    public QMediaBase(Class<? extends MediaBase> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addedBy = inits.isInitialized("addedBy") ? new reevent.domain.QUser(forProperty("addedBy")) : null;
    }

}

