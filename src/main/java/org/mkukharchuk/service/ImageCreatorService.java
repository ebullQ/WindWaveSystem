package org.mkukharchuk.service;

import org.mkukharchuk.dao.ImageDAO;
import org.mkukharchuk.model.Image;
import org.mkukharchuk.util.image.ArrayCreator;
import org.mkukharchuk.util.image.ArrayInterpolator;
import org.mkukharchuk.util.image.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ImageCreatorService {

    @Value("${upload.path}")
    String uploadPath;
    @Autowired
    private ArrayInterpolator interpolator;
    @Autowired
    private ArrayCreator creator;
    @Autowired
    private Color color;
    @Autowired
    private ImageDAO dao;

    public void writeImage(){
        BufferedImage bufferedImage = getDrawnImage();
        String path = new File("").getAbsolutePath() + uploadPath;
        try {
            File uploadDir = new File(path);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String filename = getFileName();
            ImageIO.write(bufferedImage,
                    "png",
                    new File(path + "/" + filename));
            System.out.println("[LOG] Image creating: success");
        } catch (IOException e) {
            System.out.println("[LOG] Image creating: failure");
        }
    }

    private String getFileName(){
        LocalDateTime dataTime = LocalDateTime.now();
        Image image = new Image();
        String filename = dataTime
                .toString()
                .replace(".","-")
                .replace(":", "-")+ ".png";
        image.setFileName(filename);
        image.setDate(dataTime.getDayOfMonth() + "-" + + dataTime.getMonthValue() + "-" + dataTime.getYear());
        image.setTime(dataTime.getHour());
        dao.saveImage(image);
        return filename;
    }

    private BufferedImage getDrawnImage(){
        double[][] map = interpolator.getMap(creator.getMap());
        int width = map[0].length;
        int height = map.length;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int pixelColor;
        for (int i = 0; i< height; i++) {
            for (int j = 0; j < width; j++) {
                pixelColor = color.getColor(map[i][j]);
                bufferedImage.setRGB(j, i, pixelColor);
            }
        }
        System.out.println("[LOG] Image has drawn");
        return bufferedImage;
    }










}
