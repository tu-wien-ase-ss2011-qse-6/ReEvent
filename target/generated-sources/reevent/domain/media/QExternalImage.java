package reevent.domain.media;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QExternalImage is a Querydsl query type for ExternalImage
 */
public class QExternalImage extends EntityPathBase<ExternalImage> {

    private static final long serialVersionUID = 1270335875;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QExternalImage externalImage = new QExternalImage("externalImage");

    public final QExternalMedia _super;

    // inherited
    public final reevent.domain.QUser addedBy;

    //inherited
    public final DateTimePath<java.util.Date> createdAt;

    //inherited
    public final ComparablePath<java.util.UUID> id;

    //inherited
    public final DateTimePath<java.util.Date> updatedAt;

    //inherited
    public final StringPath url;

    public QExternalImage(String variable) {
        this(ExternalImage.class, forVariable(variable), INITS);
    }

    public QExternalImage(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExternalImage(PathMetadata<?> metadata, PathInits inits) {
        this(ExternalImage.class, metadata, inits);
    }

    public QExternalImage(Class<? extends ExternalImage> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QExternalMedia(type, metadata, inits);
        this.addedBy = _super.addedBy;
        this.createdAt = _super.createdAt;
        this.id = _super.id;
        this.updatedAt = _super.updatedAt;
        this.url = _super.url;
    }

}

