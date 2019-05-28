package org.mkukharchuk.util.data;

import org.mkukharchuk.model.Wind;
import org.mkukharchuk.service.WindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Component
public class DataWriter {
    @Value("${swan.instructions.file.path}")
    private String swanInstrutions;
    private final WindService service;
    private File file;

    @Autowired
    public DataWriter(WindService service) {
        this.service = service;
        file = new File(swanInstrutions);
    }


    public void writeData() {
        Wind lastWind = service.getLastWind();
        String newData = lastWind.toString();
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(getUpdatedFile(newData));
            System.out.println("[LOG] File recreating: successful ");
        } catch (IOException e) {
            System.out.println("[LOG] File recreating: failure ");
        }
    }

    private String getUpdatedFile(String newData) {
        StringBuilder updateData = new StringBuilder();
        String line;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                if (line.startsWith("WIND")) {
                    updateData.append("WIND ").append(newData).append("\r\n");
                } else
                    updateData.append(line).append("\r\n");
            }
            System.out.println("[LOG] Data writing: successful ");
        } catch (FileNotFoundException e) {
            System.out.println("[LOG] Data writing: failure ");
        }
        return updateData.toString();
    }
}
