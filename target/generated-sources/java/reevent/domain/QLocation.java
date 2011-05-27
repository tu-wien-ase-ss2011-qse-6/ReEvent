package reevent.domain;


import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QLocation is a Querydsl query type for Location
 */
public class QLocation extends BeanPath<Location> {

    private static final long serialVersionUID = 766245400;

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final StringPath locationAddress = createString("locationAddress");

    public final StringPath locationName = createString("locationName");

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public QLocation(BeanPath<? extends Location> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QLocation(PathMetadata<?> metadata) {
        super(Location.class, metadata);
    }

}

