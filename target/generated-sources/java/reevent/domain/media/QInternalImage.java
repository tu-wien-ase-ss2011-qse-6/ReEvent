package reevent.domain.media;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QInternalImage is a Querydsl query type for InternalImage
 */
public class QInternalImage extends EntityPathBase<InternalImage> {

    private static final long serialVersionUID = -1194103343;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QInternalImage internalImage = new QInternalImage("internalImage");

    public final QInternalMedia _super;

    // inherited
    public final reevent.domain.QUser addedBy;

    //inherited
    public final DateTimePath<java.util.Date> createdAt;

    //inherited
    public final ArrayPath<Byte> data;

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    //inherited
    public final ComparablePath<java.util.UUID> id;

    //inherited
    public final DateTimePath<java.util.Date> updatedAt;

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QInternalImage(String variable) {
        this(InternalImage.class, forVariable(variable), INITS);
    }

    public QInternalImage(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInternalImage(PathMetadata<?> metadata, PathInits inits) {
        this(InternalImage.class, metadata, inits);
    }

    public QInternalImage(Class<? extends InternalImage> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QInternalMedia(type, metadata, inits);
        this.addedBy = _super.addedBy;
        this.createdAt = _super.createdAt;
        this.data = _super.data;
        this.id = _super.id;
        this.updatedAt = _super.updatedAt;
    }

}

