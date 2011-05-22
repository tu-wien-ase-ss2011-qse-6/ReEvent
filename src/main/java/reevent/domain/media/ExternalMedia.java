package reevent.domain.media;

import javax.persistence.Entity;

/**
 * External media (e.g. images hosted at other services, embedded videos...)
 */
@Entity
public abstract class ExternalMedia extends MediaBase {
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected ExternalMedia(String url) {
        this.url = url;
    }

    protected ExternalMedia() {
    }
}
