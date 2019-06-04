package org.mkukharchuk.dao;

import org.mkukharchuk.model.Image;

public interface ImageDAO {

    Image getImageById(int id);
    Image getLastImage();
    void saveImage(Image image);
}
