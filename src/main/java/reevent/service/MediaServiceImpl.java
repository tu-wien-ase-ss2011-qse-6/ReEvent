package reevent.service;

import com.mortennobel.imagescaling.DimensionConstrain;
import com.mortennobel.imagescaling.ResampleOp;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reevent.dao.MediaDao;
import reevent.domain.User;
import reevent.domain.media.InternalImage;
import reevent.domain.media.MediaBase;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    MediaDao mediaDao;

    @Override
    public MediaBase create(FileUpload upload, User addedBy) {
        try {
            return createImage(upload.getInputStream(), addedBy);
        } catch (IOException e) {
            throw new MediaUploadException(e);
        }
    }

    @Override
    public MediaBase create(URL url, User addedBy) {
        // handle videos

        InputStream is = null;
        try {
            is = url.openStream();
            return createImage(is, addedBy);
        } catch (IOException e) {
            throw new MediaUploadException(e);
        } finally {
            if (is != null) try {
                is.close();
            } catch (IOException e) {
                throw new MediaUploadException(e);
            }
        }
    }

    @Override
    public MediaBase create(String url, User addedBy) {
        try {
            return create(new URL(url), addedBy);
        } catch (MalformedURLException e) {
            throw new MediaUploadException(e);
        }
    }

    static InternalImage placeholder;

    @Override
    public InternalImage getPlaceholder() {
        if (placeholder == null) {
            BufferedImage blank = new BufferedImage(MAX_WIDTH, MAX_HEIGHT, BufferedImage.TYPE_INT_RGB);

            Graphics2D g = blank.createGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, MAX_WIDTH, MAX_HEIGHT);
            g.dispose();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            try {
                ImageIO.write(blank, "jpg", buffer);
            } catch (IOException e) {
                throw new RuntimeException(e); // should never happen
            }
            placeholder = new InternalImage();
            placeholder.setHeight(MAX_HEIGHT);
            placeholder.setWidth(MAX_WIDTH);
            placeholder.setData(buffer.toByteArray());
        }
        return placeholder;
    }

    final int MAX_WIDTH=320;
    final int MAX_HEIGHT=240;

    private InternalImage createImage(InputStream is, User addedBy) throws IOException {
        BufferedImage fullSize = ImageIO.read(is);

        // resize image
        DimensionConstrain scale = DimensionConstrain.createMaxDimension(MAX_WIDTH, MAX_HEIGHT);
        BufferedImage scaled = new ResampleOp(scale).filter(fullSize, null);

        // write out
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ImageIO.write(scaled, "jpg", bytes);

        InternalImage result = new InternalImage();
        result.setWidth(scaled.getWidth());
        result.setHeight(scaled.getHeight());
        result.setData(bytes.toByteArray());
        result.setAddedBy(addedBy);
        return mediaDao.save(result);
    }

}
