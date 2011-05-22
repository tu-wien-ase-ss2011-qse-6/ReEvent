package reevent.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class EntityBase implements Serializable {
    @Id
    @Column(columnDefinition = "UUID")
    UUID id = UUID.randomUUID();
    @Version
    Date updatedAt;

    /**
     * Time when entity was created.
     */
    Date createdAt = new Date();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityBase)) return false;

        EntityBase that = (EntityBase) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Returns the first part of the UUID, for use in toString and such.
     * @return
     */
    public static String shortId(EntityBase entity) {
        return String.format("%x", entity.getId().getMostSignificantBits()>>>32);
    }

    public String getShortId() {
        return shortId(this);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", shortId(this)).
                append("createdAt", createdAt).
                append("updatedAt", updatedAt).
                toString();
    }
}
