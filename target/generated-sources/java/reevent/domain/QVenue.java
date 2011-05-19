package java.reevent.domain;

import static com.mysema.query.types.PathMetadataFactory.*;
import reevent.domain.Event;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
// * QVenue is a Querydsl query type for Venue
// */
//public class QVenue extends EntityPathBase<Venue> {
//
//    private static final long serialVersionUID = 1081598700;
//
//    private static final PathInits INITS = PathInits.DIRECT;
//
//    public static final QVenue venue = new QVenue("venue");
//
//    public final QEntityBase _super = new QEntityBase(this);
//
//    public final StringPath address = createString("address");
//
//    //inherited
//    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;
//
//    public final SetPath<Event, QEvent> events = this.<Event, QEvent>createSet("events", Event.class, QEvent.class);
//
//    //inherited
//    public final ComparablePath<java.util.UUID> id = _super.id;
//
//    public final QLocation location;
//
//    public final StringPath name = createString("name");
//
//    public final BooleanPath stage = createBoolean("stage");
//
//    public final StringPath type = createString("type");
//
//    //inherited
//    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;
//
//    public QVenue(String variable) {
//        this(Venue.class, forVariable(variable), INITS);
//    }
//
//    public QVenue(PathMetadata<?> metadata) {
//        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
//    }
//
//    public QVenue(PathMetadata<?> metadata, PathInits inits) {
//        this(Venue.class, metadata, inits);
//    }
//
//    public QVenue(Class<? extends Venue> type, PathMetadata<?> metadata, PathInits inits) {
//        super(type, metadata, inits);
//        this.location = inits.isInitialized("location") ? new QLocation(forProperty("location")) : null;
//    }
//
//}

