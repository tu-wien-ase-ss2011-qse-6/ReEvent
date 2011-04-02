package reevent.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class EntityBase implements Serializable {
    @Id
    @Column(columnDefinition = "UUID")
    protected UUID id = UUID.randomUUID();
    @Version
    protected Integer version;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
    public String getShortId() {
        return String.format("%x", id.getMostSignificantBits()>>32);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getShortId())
                .append("version", version)
                .toString();
    }
}
