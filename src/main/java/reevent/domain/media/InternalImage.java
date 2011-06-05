package reevent.domain.media;

import javax.persistence.Entity;

@Entity
public class InternalImage extends InternalMedia {
    Integer width;
    Integer height;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
