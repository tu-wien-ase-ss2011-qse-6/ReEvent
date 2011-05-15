package reevent.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QLocation is a Querydsl query type for Location
 */
public class QLocation extends EntityPathBase<Location> {

    private static final long serialVersionUID = 766245400;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QLocation location = new QLocation("location");

    public final QEntityBase _super = new QEntityBase(this);

    public final StringPath address = createString("address");

    public final QGpsCoords coords;

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final SetPath<Event, QEvent> events = this.<Event, QEvent>createSet("events", Event.class, QEvent.class);

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final StringPath name = createString("name");

    public final BooleanPath stage = createBoolean("stage");

    public final StringPath type = createString("type");

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QLocation(String variable) {
        this(Location.class, forVariable(variable), INITS);
    }

    public QLocation(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLocation(PathMetadata<?> metadata, PathInits inits) {
        this(Location.class, metadata, inits);
    }

    public QLocation(Class<? extends Location> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.coords = inits.isInitialized("coords") ? new QGpsCoords(forProperty("coords")) : null;
    }

}

