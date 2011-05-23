package reevent.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QFeedback is a Querydsl query type for Feedback
 */
public class QFeedback extends EntityPathBase<Feedback> {

    private static final long serialVersionUID = -1326299672;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QFeedback feedback = new QFeedback("feedback");

    public final QEntityBase _super = new QEntityBase(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final QUser createdBy;

    public final QEvent event;

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final EnumPath<Rating> rating = createEnum("rating", Rating.class);

    public final StringPath text = createString("text");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QFeedback(String variable) {
        this(Feedback.class, forVariable(variable), INITS);
    }

    public QFeedback(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFeedback(PathMetadata<?> metadata, PathInits inits) {
        this(Feedback.class, metadata, inits);
    }

    public QFeedback(Class<? extends Feedback> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUser(forProperty("createdBy")) : null;
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event"), inits.get("event")) : null;
    }

}

