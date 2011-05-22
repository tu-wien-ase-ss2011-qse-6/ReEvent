package reevent.domain.media;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QInternalMedia is a Querydsl query type for InternalMedia
 */
public class QInternalMedia extends EntityPathBase<InternalMedia> {

    private static final long serialVersionUID = -1190644646;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QInternalMedia internalMedia = new QInternalMedia("internalMedia");

    public final QMediaBase _super;

    // inherited
    public final reevent.domain.QUser addedBy;

    //inherited
    public final DateTimePath<java.util.Date> createdAt;

    public final ArrayPath<Byte> data = createArray("data", Byte[].class);

    //inherited
    public final ComparablePath<java.util.UUID> id;

    //inherited
    public final DateTimePath<java.util.Date> updatedAt;

    public QInternalMedia(String variable) {
        this(InternalMedia.class, forVariable(variable), INITS);
    }

    public QInternalMedia(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInternalMedia(PathMetadata<?> metadata, PathInits inits) {
        this(InternalMedia.class, metadata, inits);
    }

    public QInternalMedia(Class<? extends InternalMedia> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QMediaBase(type, metadata, inits);
        this.addedBy = _super.addedBy;
        this.createdAt = _super.createdAt;
        this.id = _super.id;
        this.updatedAt = _super.updatedAt;
    }

}

