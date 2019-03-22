package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UpdateDataService {

    public void executeProcess(){
        String values = getValues();
        writeData(values);

        File dir = new File("C:\\Users\\bull3\\Desktop\\wws\\");
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("cmd","/C","start", "naswan.bat");
        builder.directory(dir);
        try {
            System.out.println("[LOG] Process started...");
            Process process = builder.start();
            Thread.sleep(100000);
            System.out.println("[LOG] Process completed!");
        } catch (IOException | InterruptedException e) {
            System.out.println("[LOG] Process was terminated");
        }
    }



    private String getValues() {
        Document doc;
        String body = null;
        try {
            doc = Jsoup.connect("http://lcss.ocean.ru/meteo1pi/dat").get();
            System.out.println("[LOG] Data access: successful");
            body = doc.body().text();
        } catch (
                IOException e) {
            System.out.println("[LOG] Data access: failure");
        }
        String[] data = body.split(",");
            if(Double.parseDouble(data[3])>100){
                System.out.println("[LOG] Data correction: NOT correct ");
                return data[5]+ " " + data[6];
            }
        System.out.println("[LOG] Data correction: correct (" + data[3]+ " " + data[5] + ")");
        return data[3]+ " " + data[5];

    }

    private  void writeData(String newData) {
        File file = new File("C:\\Users\\bull3\\Desktop\\wws\\Curonian.swn");

        StringBuilder updateData = new StringBuilder();
        String line;
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()){
                line = scanner.nextLine();
                if(line.startsWith("WIND")) {
                    updateData.append("WIND " + newData + "\r\n");
                }
                else
                    updateData.append(line + "\r\n");
            }
            System.out.println("[LOG] Data writing: successful ");
        } catch (FileNotFoundException e){
            System.out.println("[LOG] Data writing: failure ");
        }

        try(FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(updateData.toString());
            System.out.println("[LOG] File recreating: successful ");
        }catch (IOException e){
            System.out.println("[LOG] File recreating: failure ");
        }
    }
}
