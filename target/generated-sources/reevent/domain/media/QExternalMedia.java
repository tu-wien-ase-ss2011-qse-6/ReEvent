package reevent.domain.media;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QExternalMedia is a Querydsl query type for ExternalMedia
 */
public class QExternalMedia extends EntityPathBase<ExternalMedia> {

    private static final long serialVersionUID = 1273794572;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QExternalMedia externalMedia = new QExternalMedia("externalMedia");

    public final QMediaBase _super;

    // inherited
    public final reevent.domain.QUser addedBy;

    //inherited
    public final DateTimePath<java.util.Date> createdAt;

    //inherited
    public final ComparablePath<java.util.UUID> id;

    //inherited
    public final DateTimePath<java.util.Date> updatedAt;

    public final StringPath url = createString("url");

    public QExternalMedia(String variable) {
        this(ExternalMedia.class, forVariable(variable), INITS);
    }

    public QExternalMedia(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExternalMedia(PathMetadata<?> metadata, PathInits inits) {
        this(ExternalMedia.class, metadata, inits);
    }

    public QExternalMedia(Class<? extends ExternalMedia> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QMediaBase(type, metadata, inits);
        this.addedBy = _super.addedBy;
        this.createdAt = _super.createdAt;
        this.id = _super.id;
        this.updatedAt = _super.updatedAt;
    }

}

