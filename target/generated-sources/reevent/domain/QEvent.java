package reevent.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QEvent is a Querydsl query type for Event
 */
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = 1066396439;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QEvent event = new QEvent("event");

    public final QEntityBase _super = new QEntityBase(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final QUser createdBy;

    public final StringPath description = createString("description");

    public final SetPath<Feedback, QFeedback> feedbacks = this.<Feedback, QFeedback>createSet("feedbacks", Feedback.class, QFeedback.class);

    public final StringPath genre = createString("genre");

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final QLocation location;

    public final SetPath<reevent.domain.media.MediaBase, reevent.domain.media.QMediaBase> media = this.<reevent.domain.media.MediaBase, reevent.domain.media.QMediaBase>createSet("media", reevent.domain.media.MediaBase.class, reevent.domain.media.QMediaBase.class);

    public final StringPath name = createString("name");

    public final StringPath performer = createString("performer");

    public final DateTimePath<java.util.Date> start = createDateTime("start", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QEvent(String variable) {
        this(Event.class, forVariable(variable), INITS);
    }

    public QEvent(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEvent(PathMetadata<?> metadata, PathInits inits) {
        this(Event.class, metadata, inits);
    }

    public QEvent(Class<? extends Event> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUser(forProperty("createdBy")) : null;
        this.location = inits.isInitialized("location") ? new QLocation(forProperty("location")) : null;
    }

}

