package org.mkukharchuk.service;

import org.mkukharchuk.model.Image;
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

    public ImageService imageService;

    @Autowired
    public WindService windService;

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
        image.setWind(windService.getLastWind());
        imageService.saveImage(image);
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
        int pixelColor;
        for (int i = 0; i< height; i++) {
            for (int j = 0; j < width; j++) {
                pixelColor = getColor(map[i][j]);
                bufferedImage.setRGB(j, i, pixelColor);
            }
        }
        System.out.println("[LOG] Image has drawn");
        return bufferedImage;
    }

    private double[][] getMap(){
        File file = new File(computingResult); // MACOS
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

    public int getColor(double value) {
        if(value==-1){
            return 0xffffff;
        } else if(value<=-0.2){
            return 0xffffff;
        } else if(value<=-0.1){
            return 0x000060;
        } else if(value<=0){
            return 0x00007a;
        } else if(value<=1.5d){
            return 0x00008c;
        } else if (value <= 3d) {
            return 0x000095;
        } else if (value <= 4.5d) {
            return 0x0000ad;
        } else if (value <= 6d) {
            return 0x0001be;
        } else if (value <= 7.5d) {
            return 0x0001ce;
        } else if (value <= 9d) {
            return 0x0000db;
        } else if (value <= 10.5d) {
            return 0x0000ee;
        } else if (value <= 12d) {
            return 0x000eff;
        } else if (value <= 13.5d) {
            return 0x000fff;
        } else if (value <= 15d) {
            return 0x021eff;
        } else if (value <= 16.5d) {
            return 0x012fff;
        } else if (value <= 18d) {
            return 0x0032fd;
        } else if (value <= 19.5d) {
            return 0x0040fe;
        } else if (value <= 21d) {
            return 0x014eff;
        } else if (value <= 22.5d) {
            return 0x015fff;
        } else if (value <= 24d) {
            return 0x016dff;
        } else if (value <= 25.5d) {
            return 0x0080ff;
        } else if (value <= 27d) {
            return 0x018fff;
        } else if (value <= 28.5d) {
            return 0x008ffe;
        } else if (value <= 30d) {
            return 0x019eff;
        } else if (value <= 31.5d) {
            return 0x00afff;
        } else if (value <= 33d) {
            return 0x00bffe;
        } else if (value <= 34.5d) {
            return 0x02ceff;
        } else if (value <= 36d) {
            return 0x03dfff;
        } else if (value <= 37.5d) {
            return 0x03eeff;
        } else if (value <= 39d) {
            return 0x00effe;
        } else if (value <= 40.5d) {
            return 0x01ffff;
        } else if (value <= 42d) {
            return 0x09fff5;
        } else if (value <= 43.5d) {
            return 0x18ffe7;
        } else if (value <= 45d) {
            return 0x1dffe2;
        } else if (value <= 46.5d) {
            return 0x24feda;
        } else if (value <= 48d) {
            return 0x3cffc3;
        } else if (value <= 49.5) {
            return 0x3dffc1;
        } else if (value <= 51d) {
            return 0x4ffeaf;
        } else if (value <= 52.5d) {
            return 0x5effa2;
        } else if (value <= 54d) {
            return 0x65fe9a;
        } else if (value <= 55.5d) {
            return 0x6cff91;
        } else if (value <= 57d) {
            return 0x70ff8e;
        } else if (value <= 58.5d) {
            return 0x7eff80;
        } else if (value <= 60d) {
            return 0x8bff74;
        } else if(value<=61.5d){
            return 0x93fe6b;
        } else if (value <= 63d) {
            return 0xa5fe58;
        } else if (value <= 64.5d) {
            return 0xafff4e;
        } else if (value <= 66d) {
            return 0xc1fe3e;
        } else if (value <= 67.5d) {
            return 0xd2ff2c;
        } else if (value <= 69d) {
            return 0xdfff1e;
        } else if (value <= 70.5d) {
            return 0xe6ff19;
        } else if (value <= 72d) {
            return 0xf1fe0e;
        } else if (value <= 73.5d) {
            return 0xffff02;
        } else if (value <= 75d) {
            return 0xfff002;
        } else if (value <= 76.5d) {
            return 0xfedf00;
        } else if (value <= 78d) {
            return 0xffcf00;
        } else if (value <= 79.5d) {
            return 0xffbe00;
        } else if (value <= 81d) {
            return 0xffad01;
        } else if (value <= 82.5d) {
            return 0xff9f00;
        } else if (value <= 84d) {
            return 0xfd8e00;
        } else if (value <= 85.5d) {
            return 0xff7f00;
        } else if (value <= 87d) {
            return 0xff5e03;
        } else if (value <= 88.5d) {
            return 0xff5001;
        } else if (value <= 90d) {
            return 0xff4d03;
        } else if (value <= 91.5d) {
            return 0xff4001;
        } else if (value <= 93d) {
            return 0xfe3a02;
        } else if (value <= 94.5d) {
            return 0xff1d01;
        } else if (value <= 96d) {
            return 0xff0f01;
        } else if (value <= 97.5d) {
            return 0xfe0000;
        } else if (value <= 99d) {
            return 0xf40000;
        } else if (value <= 100.5d) {
            return 0xee0000;
        } else if (value <= 102d) {
            return 0xed0002;
        } else if (value <= 103.5d) {
            return 0xe00000;
        } else if (value <= 105d) {
            return 0xd00000;
        } else if (value <= 106.5d) {
            return 0xc00000;
        } else if (value <= 108d) {
            return 0xad0101;
        } else if (value <= 109.5) {
            return 0xa20000;
        } else if (value <= 111d) {
            return 0x9a0100;
        } else if (value <= 112.5d) {
            return 0x8f0000;
        } else if (value <= 114d) {
            return 0x7a0001;
        } else if (value <= 115.5d) {
            return 0x680000;
        } else if (value <= 117d) {
            return 0x600000;
        } else if (value <= 118.5d) {
            return 0x450000;
        }
        return 0x350000;
    }
}