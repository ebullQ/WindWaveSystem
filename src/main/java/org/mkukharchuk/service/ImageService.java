package org.mkukharchuk.service;

import org.mkukharchuk.model.Image;

public interface ImageService {
    Image getImageById(int id);
    Image getLastImage();
    void saveImage(Image image);
}
