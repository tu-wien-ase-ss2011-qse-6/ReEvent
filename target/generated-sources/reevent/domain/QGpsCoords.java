package reevent.domain;


import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QGpsCoords is a Querydsl query type for GpsCoords
 */
public class QGpsCoords extends BeanPath<GpsCoords> {

    private static final long serialVersionUID = 625797989;

    public final NumberPath<Integer> latitude = createNumber("latitude", Integer.class);

    public final NumberPath<Integer> longitude = createNumber("longitude", Integer.class);

    public QGpsCoords(BeanPath<? extends GpsCoords> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QGpsCoords(PathMetadata<?> metadata) {
        super(GpsCoords.class, metadata);
    }

}

