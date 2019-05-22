package org.mkukharchuk.util.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ProcessExecutor {

    @Value("${wws.directory}")
    private String wwsDirectory;
    @Value("${swan.bat.start.file.name}")
    private String batSwanStartFile;
    @Value("#{new Long('${millis.delay}')}")
    private long millis;

    public void executeProcess(){
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
}
