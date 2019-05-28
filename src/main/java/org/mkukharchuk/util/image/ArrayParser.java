package org.mkukharchuk.util.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class ArrayParser {

    @Value("${swan.result.map}")
    String computingResult;

    public double[][] getMap(){
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

}
