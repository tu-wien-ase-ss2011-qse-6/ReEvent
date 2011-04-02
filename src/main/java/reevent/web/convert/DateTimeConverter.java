package reevent.web.convert;

import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converter.AbstractConverter;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 02.04.2011
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeConverter extends AbstractConverter<Date> {
    private Integer dateStyle;
    private Integer timeStyle;

    private DateTimeConverter() {
    }

    public static DateTimeConverter date(int dateStyle) {
        DateTimeConverter result = new DateTimeConverter();
        result.dateStyle = dateStyle;
        return result;
    }

    public static DateTimeConverter time(int timeStyle) {
        DateTimeConverter result = new DateTimeConverter();
        result.timeStyle = timeStyle;
        return result;
    }

    public static DateTimeConverter both(int dateStyle, int timeStyle) {
        DateTimeConverter result = new DateTimeConverter();
        result.dateStyle = dateStyle;
        result.timeStyle = timeStyle;
        return result;
    }

    @Override
    protected Class<Date> getTargetType() {
        return Date.class;
    }

    @Override
    public Date convertToObject(String value, Locale locale) {
        return parse(makeFormat(locale), value, locale);
    }

    @Override
    public String convertToString(Date value, Locale locale) {
        return makeFormat(locale).format(value);
    }

    private DateFormat makeFormat(Locale locale) {
        if (dateStyle != null && timeStyle != null) {
            return DateFormat.getDateTimeInstance(dateStyle,  timeStyle,  locale);
        } else if (dateStyle != null) {
            return DateFormat.getDateInstance(dateStyle, locale);
        } else if (timeStyle != null) {
            return DateFormat.getTimeInstance(timeStyle, locale);
        } else {
            throw new IllegalStateException("Both dateStyle and timeStyle are null");
        }
    }
}
