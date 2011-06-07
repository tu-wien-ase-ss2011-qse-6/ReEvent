package reevent.service;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import reevent.domain.User;
import reevent.domain.media.InternalImage;
import reevent.domain.media.MediaBase;

import java.net.URL;

public interface MediaService {
    MediaBase create(FileUpload upload, User addedBy);

    MediaBase create(URL uri, User addedBy);

    MediaBase create(String s, User addedBy);

    InternalImage getPlaceholder();
}
