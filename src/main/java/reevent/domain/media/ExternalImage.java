package reevent.domain.media;

import javax.persistence.Entity;

@Entity
public class ExternalImage extends ExternalMedia {
    public ExternalImage() {
    }

    public ExternalImage(String url) {
        super(url);
    }
}
