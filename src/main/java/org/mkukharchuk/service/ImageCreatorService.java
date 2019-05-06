package org.mkukharchuk.service;

import org.mkukharchuk.dao.ImageDAO;
import org.mkukharchuk.model.Image;
import org.mkukharchuk.util.color.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

@Service
public class ImageCreatorService {

    @Value("${upload.path}")
    String uploadPath;

    @Value("${swan.result.map}")
    String computingResult;

    @Autowired
    public ImageDAO dao;

    public void writeImage(){
        BufferedImage bufferedImage = getDrawnImage();
        try {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String filename = getFileName();
            //File img = new File(uploadDir.getAbsolutePath() + "/" + "img2.png");
            ImageIO.write(bufferedImage,
                    "png",
                    new File(uploadDir + "/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
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
        dao.saveUser(image);
        return filename;
    }

    private BufferedImage getDrawnImage(){
        double[][] map = getMap();
        for (int i = 0; i < 3  ; i++) {
            map = getIterpolationMap(map);
        }
        int width = map[0].length;
        int height = map.length;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Color color = new Color();
        int pixelColor;
        for (int i = 0; i< height; i++) {
            for (int j = 0; j < width; j++) {
                pixelColor = color.getColor(map[i][j]);
                bufferedImage.setRGB(j, i, pixelColor);
            }
        }
        return bufferedImage;
    }

    private double[][] getMap(){
        File file = new File("/Users/matvey/IdeaProjects/WindWaveSystem/wws/curres.txt"); // MACOS
        double[][] map = new double[188][105];
        try(Scanner scanner = new Scanner(file)){
            String line = null;
            while(scanner.hasNext()){
                line = scanner.nextLine();
                if(line.startsWith("  188")){
                    break;
                }
            }
            int currentLineNumber = 188;
            while(scanner.hasNext()) {
                int lineIndex = Math.abs(188 - currentLineNumber);
                String sortedLine = getSortedLine(line, currentLineNumber);
                double[] numbers = getDoubleArray(sortedLine.split(" "));
                System.arraycopy(numbers, 0, map[lineIndex], 0, numbers.length);
                line = scanner.nextLine();
                currentLineNumber--;
            }
        }catch (FileNotFoundException e){
            e.getMessage();
        }
        return map;
    }

    private String getSortedLine(String line, int currentLine){
        int counter=0;
        StringBuilder numbers= new StringBuilder();
        line = line.trim()
                .replaceFirst(currentLine+ " ","")
                .replaceAll(" ","");
        for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i)=='*'){
                counter++;
                if(counter==4){
                    numbers.append(-1    + " ");
                    counter=0;
                }
            }else if(line.charAt(i)=='.'){
                numbers.append(' ');
            }else {
                numbers.append(line.charAt(i));
            }
        }
        return numbers.toString();
    }

    private double[] getDoubleArray(String[] array){
        double[] doubles  = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubles[i] = Integer.parseInt(array[i]);
        }
        return doubles;
    }

    private double[][] getIterpolationMap(double[][] array){
        double[][] doubles = new double[array.length*2-1][array[0].length*2-1];
        int k = 0;
        int m = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <array[0].length; j++) {
                doubles[k][m++] = array[i][j];
                if(m<doubles[i].length) {
                    doubles[k][m++] = (array[i][j] + array[i][j + 1]) / 2;
                }
            }
            k = k + 2;
            m=0;
        }
        for (int i = 1; i < doubles.length; i=i+2) {
            for (int j = 0; j < doubles[i].length; j++) {
                doubles[i][j] = (doubles[i-1][j]+doubles[i+1][j])/2;
            }
        }
        return doubles;
    }

    private int getColor(double value) {
        if(value==-1){
            return 0xffffff;
        }
        if(value<=0){
            return 0x0019ff;
        }
        if(value<=1d){
            return 0x0019ff;
        }
        else if(value<=1.25d) {
            return 0x0019ff;
        }
        else if(value<=2.5d) {
            return 0x0033ff;
        }
        else if(value<=3.75d) {
            return 0x004cff;
        }
        else if(value<=5d) {
            return 0x0065ff;
        }
        else if(value<=6.25d) {
            return 0x007fff;
        }
        else if(value<=7.5d) {
            return 0x0099ff;
        }
        else if(value<=8.75d) {
            return 0x00b2ff;
        }
        else if(value<=10) {
            return 0x00cbff;
        }
        else if(value<=11.25d) {
            return 0x00e5ff;
        }
        else if(value<=12.5d) {
            return 0x00ffff;
        }
        else if(value<=13.75d) {
            return 0x00ffe5;
        }
        else if(value<=15d) {
            return 0x00ffcb;
        }
        else if(value<=16.25d) {
            return 0x00ffb2;
        }
        else if(value<=17.5d) {
            return 0x00ff99;
        }
        else if(value<=18.75d) {
            return 0x00ff7f;
        }
        else if(value<=20) {
            return 0x00ff65;
        }
        else if(value<=21.25d) {
            return 0x00ff4c;
        }
        else if(value<=22.5d) {
            return 0x00ff33;
        }
        else if(value<=23.75d) {
            return 0x00ff19;
        }
        else if(value<=25d) {
            return 0x00ff00;
        }
        else if(value<=26.25d) {
            return 0x19ff00;
        }
        else if(value<=27.5d) {
            return 0x32ff00;
        }
        else if(value<=28.75d) {
            return 0x4cff00;
        }
        else if(value<=30d) {
            return 0x65ff00;
        }
        else if(value<=31.25d) {
            return 0x7fff00;
        }
        else if(value<=32.5d) {
            return 0x99ff00;
        }
        else if(value<=33.75d) {
            return 0xb2ff00;
        }
        else if(value<=35d) {
            return 0xccff00;
        }
        else if(value<=36.25d) {
            return 0xe5ff00;
        }
        else if(value<=37.5d) {
            return 0xffff00;
        }
        else if(value<=38.75d) {
            return 0xffe500;
        }
        else if(value<=40d) {
            return 0xffcc00;
        }
        else if(value<=41.25d) {
            return 0xffb200;
        }
        else if(value<=42.5d) {
            return 0xff9900;
        }
        else if(value<=43.75d) {
            return 0xff7f00;
        }
        else if(value<=45d) {
            return 0xff6600;
        }
        else if(value<=46.25d) {
            return 0xff4c00;
        }
        else if(value<=47.5d) {
            return 0xff3200;
        }
        else if(value<=48.75d) {
            return 0xff1900;
        }
        else if(value<=50d) {
            return 0xff0000;
        }
        else
            return 0x9b0000;
    }

}
