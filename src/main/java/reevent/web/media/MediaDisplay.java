package reevent.web.media;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import reevent.dao.MediaDao;
import reevent.domain.media.MediaBase;
import reevent.util.ClassHandlerResolver;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

public class MediaDisplay extends Panel {
    static ClassHandlerResolver<MediaBase, Class<? extends Component>> factories
            = new ClassHandlerResolver<MediaBase, Class<? extends Component>>();
    static List<? extends Class<? extends Component>> FACTORIES = asList(
            InternalImageDisplay.class);
    private UUID mediaId;

    static {
        for (Class<? extends Component> factoryClass : FACTORIES) {
            Class<? extends MediaBase> mediaClass = factoryClass.getAnnotation(Displays.class).value();
            factories.register(mediaClass, factoryClass);
        }
    }

    @SpringBean
    MediaDao mediaDao;

    public MediaDisplay(String id, UUID mediaId) {
        super(id);
        this.setRenderBodyOnly(true);
        this.mediaId = mediaId;

        Class<? extends Component> factory = factories.resolve(mediaDao.get(mediaId));
        try {
            Component displayComponent = factory.getConstructor(String.class, UUID.class).newInstance(id, mediaId);
            this.add(displayComponent);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
