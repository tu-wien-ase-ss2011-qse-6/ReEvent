package java.reevent.domain;

import static com.mysema.query.types.PathMetadataFactory.*;
import reevent.domain.Event;

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

    public final StringPath band = createString("band");

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final StringPath genre = createString("genre");

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final QLocation location;

    public final StringPath name = createString("name");

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
        this.location = inits.isInitialized("location") ? new QLocation(forProperty("location"), inits.get("location")) : null;
    }

}

