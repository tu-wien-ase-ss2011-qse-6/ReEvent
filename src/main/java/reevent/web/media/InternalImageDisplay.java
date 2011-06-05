package reevent.web.media;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import reevent.dao.MediaDao;
import reevent.domain.media.InternalImage;

import java.util.UUID;

@Displays(InternalImage.class)
public class InternalImageDisplay extends Panel {
    UUID mediaId;

    @SpringBean
    MediaDao mediaDao;

    public InternalImageDisplay(String id, UUID mediaId) {
        super(id);
        this.setRenderBodyOnly(true);
        this.mediaId = mediaId;

        final InternalImage imageData = (InternalImage) mediaDao.load(mediaId);

        Image img = new Image("image", new ByteArrayResource("image/jpeg", imageData.getData())) {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("width", imageData.getWidth());
                tag.put("height", imageData.getHeight());
            }
        };
        this.add(img);
    }
}