package reevent.domain.media;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
/**
 * Media stored in the database.
 */
public abstract class InternalMedia extends MediaBase {
    @Lob
    byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    protected InternalMedia(byte[] data) {
        this.data = data;
    }

    protected InternalMedia() {
    }
}
