package reevent.util;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


public class WicketErrorUtil {
    public static String getError(Component scope, String prefix, Exception ex) {
        return getError(scope, prefix, ex, null);
    }

    public static String getError(Component scope, String prefix, Exception ex, IModel<?> vars) {
        String defaultMessage = scope.getString(prefix, Model.of(ExceptionUtils.getRootCauseMessage(ex)));
        return scope.getString(prefix + "." + ex.getClass().getSimpleName(), vars, defaultMessage);
    }
}
