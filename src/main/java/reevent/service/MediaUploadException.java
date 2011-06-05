package reevent.service;

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 05.06.2011
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
public class MediaUploadException extends RuntimeException {
    public MediaUploadException() {
    }

    public MediaUploadException(Throwable cause) {
        super(cause);
    }

    public MediaUploadException(String message) {
        super(message);
    }

    public MediaUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
