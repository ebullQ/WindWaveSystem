package service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ImageCreatorService {

    public void makeImage(){
        //UpdateDataService dataService = new UpdateDataService();
        //dataService.executeProcess();

        double[][] map = getMap();
        for (int i = 0; i < 3; i++) {
            map = getIterpolationMap(map);
        }

        int width = map.length;
        int height = map[0].length;
        BufferedImage bufferedImage = new BufferedImage(height,width,BufferedImage.TYPE_INT_RGB);
        int color;
        for (int i = 0; i< width; i++) {
            for (int j = 0; j < height; j++) {
                color = getColor(map[i][j]);
                bufferedImage.setRGB(j, i, color);
            }
        }
        try {
            //ImageIO.write(bufferedImage,"png",new File("C:\\Users\\bull3\\Desktop\\wws\\testNEWv6.png")); WINDOWS
            ImageIO.write(bufferedImage,"png",new File("/Users/matvey/IdeaProjects/WindWaveSystem/wws/img.png")); // MACOS
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static double[][] getMap(){
        //File file = new File("C:\\Users\\bull3\\Desktop\\wws\\curres.txt"); WINDOWS
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
                double[] numbers = getIntArray(sortedLine.split(" "));
                System.arraycopy(numbers, 0, map[lineIndex], 0, numbers.length);
                line = scanner.nextLine();
                currentLineNumber--;
            }

        }catch (FileNotFoundException e){
            e.getMessage();
        }

        return map;
    }

    private static String getSortedLine(String line, int currentLine){
        line = line.trim();
        line = line.replaceFirst(currentLine+ " ","");
        line = line.replaceAll(" ","");
        int counter=0;
        StringBuilder numbers= new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i)=='*'){
                counter++;
                if(counter==4){
                    numbers.append(0 + " ");
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

    private static double[] getIntArray(String[] array){
        double[] doubles  = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubles[i] = Integer.parseInt(array[i]);
        }
        return doubles;
    }

    private static double[][] getIterpolationMap(double[][] array){
        double[][] doubles = new double[array.length*2-1][array[0].length*2-1];
        int k = 0;
        int m = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <array[0].length; j++) {
                doubles[k][m++] = array[i][j];
//                if(m<doubles[i].length && array[i][j]==0 && array[i][j+1]!=0){
//                    doubles[k][m++] = array[i][j];
//                    continue;
//                }
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
        if(value==0){
            return 0xffffff;
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
        else if(value<=30) {
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
        else if(value<=40) {
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
        else if(value<=50) {
            return 0xff0000;
        }
        else

            return 0x9b0000;
    }

}
