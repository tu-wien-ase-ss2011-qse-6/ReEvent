package reevent.web.media;

import reevent.domain.media.MediaBase;

public @interface Displays {
    Class<? extends MediaBase> value();
}
