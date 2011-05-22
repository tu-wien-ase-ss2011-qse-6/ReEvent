package reevent.domain;


import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QLocation is a Querydsl query type for Location
 */
public class QLocation extends BeanPath<Location> {

    private static final long serialVersionUID = 766245400;

    public final StringPath address = createString("address");

    public final NumberPath<Integer> latitude = createNumber("latitude", Integer.class);

    public final NumberPath<Integer> longitude = createNumber("longitude", Integer.class);

    public final StringPath name = createString("name");

    public QLocation(BeanPath<? extends Location> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QLocation(PathMetadata<?> metadata) {
        super(Location.class, metadata);
    }

}

