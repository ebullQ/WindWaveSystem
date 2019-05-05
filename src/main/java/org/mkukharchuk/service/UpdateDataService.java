package org.mkukharchuk.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*Thar service take data
from the website where
the wind data places. */
@Service
public class UpdateDataService {
    @Value("${wws.directory}")
    String wwsDirectory;
    @Value("${data.source}")
    String dataSource;
    @Value("${swan.instructions.file.path}")
    String swanInstrutions;
    @Value("${swan.bat.start.file.name}")
    String batSwanStartFile;
    @Value("#{new Long('${millis.delay}')}")
    long millis;

    /* Create process which
    execute SWAN computing with a new data.
    Current thread stops for n-millisecond.
    It depends how many time process is going to last*/
    public void executeProcess(){
        String values = getValues();
        writeData(values);
        File dir = new File(wwsDirectory);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("cmd","/C","start", batSwanStartFile);
        builder.directory(dir);
        try {
            System.out.println("[LOG] Process started...");
            builder.start();
            Thread.sleep(millis);
            System.out.println("[LOG] Process completed!");
        } catch (IOException | InterruptedException e) {
            System.out.println("[LOG] Process was terminated");
        }
    }

    /*There is connecting to the website
    with data and taking current wind
    speed and direction*/
    private String getValues() {
        Document doc;
        String body = null;
        try {
            doc = Jsoup.connect(dataSource).get();
            System.out.println("[LOG] Data access: successful");
            body = doc.body().text();
        } catch (IOException e) {
            System.out.println("[LOG] Data access: failure");
        }
        String[] data = body.split(",");
            if(Double.parseDouble(data[3]) > 100){
                System.out.println("[LOG] Data correction: NOT correct ");
                return data[5]+ " " + data[6];
            }
        System.out.println("[LOG] Data correction: correct (" + data[3]+ " " + data[5] + ")");
        return data[3]+ " " + data[5];

    }

    /*Replace the old data
    from a file with the new one*/
    private void writeData(String newData) {
        File file = new File(swanInstrutions);
        StringBuilder updateData = new StringBuilder();
        String line;
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()){
                line = scanner.nextLine();
                if(line.startsWith("WIND")) {
                    updateData.append("WIND ").append(newData).append("\r\n");
                }
                else
                    updateData.append(line).append("\r\n");
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
