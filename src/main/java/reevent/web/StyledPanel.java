package reevent.web;


import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.lang.reflect.Modifier;

/**
 * A Panel that automatically appends a CSS class to the component according to its class name.
 */
public abstract class StyledPanel extends Panel {
    protected StyledPanel(String id) {
        super(id);
    }

    protected StyledPanel(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        Class<?> cls = this.getClass();
        while (!Modifier.isAbstract(cls.getModifiers())) {
            String name = cls.getSimpleName();
            String[] words = name.split("(?<=\\p{javaLowerCase})(?=\\p{javaUpperCase})");
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].toLowerCase();
            }
            tag.append("class", StringUtils.join(words, '-'), " ");
            cls = cls.getSuperclass();
        }
    }
}
