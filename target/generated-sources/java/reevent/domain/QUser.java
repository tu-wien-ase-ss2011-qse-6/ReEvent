package java.reevent.domain;

import static com.mysema.query.types.PathMetadataFactory.*;
import reevent.domain.User;
import reevent.domain.UserRole;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QUser is a Querydsl query type for User
 */
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -380768338;

    public static final QUser user = new QUser("user");

    public final QEntityBase _super = new QEntityBase(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final DateTimePath<java.util.Date> dayOfBirth = createDateTime("dayOfBirth", java.util.Date.class);

    public final StringPath firstName = createString("firstName");

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final StringPath lastName = createString("lastName");

    public final StringPath passwordHash = createString("passwordHash");

    public final SetPath<UserRole, EnumPath<UserRole>> roles = this.<UserRole, EnumPath<UserRole>>createSet("roles", UserRole.class, EnumPath.class);

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(BeanPath<? extends User> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QUser(PathMetadata<?> metadata) {
        super(User.class, metadata);
    }

}

