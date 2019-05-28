package org.mkukharchuk.service.impl;
import org.mkukharchuk.dao.ImageDAO;
import org.mkukharchuk.model.Image;
import org.mkukharchuk.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDAO dao;

    @Override
    public Image getImageById(int id) {
        return dao.getImageById(id);
    }

    @Override
    public Image getLastImage() {
        return dao.getLastImage();
    }

    @Override
    @Transactional
    public void saveImage(Image image) {
        dao.saveImage(image);
    }
}
