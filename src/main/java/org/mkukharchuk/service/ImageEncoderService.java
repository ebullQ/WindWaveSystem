package org.mkukharchuk.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

@Service
public class ImageEncoderService {

    public static String encoder(String imagePath) {
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }

    private static double[][] getBileniarInterpolyation(double[][] array){
        double[][] doubles = new double[array.length*2-1][array[0].length*2-1];
        int k = 0;
        int m = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <array[0].length; j++) {
                doubles[k][m] = array[i][j];
                m += 2;
            }
            k += 2;
            m=0;
        }
        for (int i = 1; i < doubles.length; i+=2) {
            for (int j = 1; j <doubles[0].length; j+=2) {
                doubles[i][j] = (doubles[i-1][j-1]+doubles[i-1][j+1]+doubles[i+1][j-1]+doubles[i+1][j+1])/4;
            }
        }
        for (int i = 0; i < doubles.length; i++) {
            for (int j = 0; j <doubles[0].length; j++) {
                if(i%2==0){
                    if(j==doubles[0].length-1)
                        continue;
                    j++;
                    if(i==0){
                        doubles[i][j]=Math.round((((doubles[i][j-1] + doubles[i][j+1] + doubles[i+1][j]) / 3)*10.0))/10.0;
                    }
                    else if(i==doubles.length-1){
                        doubles[i][j]=Math.round((((doubles[i][j-1] + doubles[i][j+1] + doubles[i-1][j]) / 3)*10.0))/10.0;
                    }
                    else{
                        doubles[i][j]=Math.round((((doubles[i][j-1] + doubles[i][j+1] + doubles[i-1][j] + doubles[i+1][j]) / 4)*10.0))/10.0;
                    }
                }
                else{
                    if(j==0){
                        doubles[i][j]=Math.round((((doubles[i][j+1] + doubles[i-1][j] + doubles[i+1][j]) / 3)*10.0))/10.0;
                    }
                    else if(j==doubles[0].length-1){
                        doubles[i][j]=Math.round((((doubles[i][j-1] + doubles[i-1][j] + doubles[i+1][j]) / 3) * 10.0))/10.0;
                    }
                    else{
                        doubles[i][j]=Math.round((((doubles[i-1][j] + doubles[i+1][j] + doubles[i][j-1] + doubles[i][j+1]) / 4)*10.0))/10.0;
                    }
                    j++;
                }
            }
        }
        return doubles;
    }

    private static double[][] getNigberInterpolyation(double[][] array){
        double[][] doubles = new double[array.length*2][array[0].length*2];
        int k = 0;
        int m = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <array[0].length; j++) {
                doubles[k][m] = array[i][j];
                doubles[k+1][m++] = array[i][j];
                doubles[k][m] = array[i][j];
                doubles[k+1][m++] = array[i][j];
            }
            k = k + 2;
            m=0;
        }
        return doubles;
    }



}
