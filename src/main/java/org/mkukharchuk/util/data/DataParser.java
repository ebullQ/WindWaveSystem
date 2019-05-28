package org.mkukharchuk.util.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mkukharchuk.model.Wind;
import org.mkukharchuk.service.WindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataParser {
    @Value("${data.source}")
    private String dataSource;
    private final WindService service;

    @Autowired
    public DataParser(WindService service) {
        this.service = service;
    }

    public void getValues() {
        Document doc;
        String body = null;
        try {
            doc = Jsoup.connect(dataSource).get();
            System.out.println("[LOG] Data access: successful");
            body = doc.body().text();
        } catch (IOException e) {
            System.out.println("[LOG] Data access: failure");
        }
        String[] data = body != null ? body.split(",") : new String[6];
        System.out.println("[LOG] Data correction: correct (" + data[3]+ " " + data[5] + ")");
        Wind wind = new Wind();
        wind.setSpeed(Double.parseDouble(data[3]));
        wind.setDirection(Integer.parseInt(data[5]));
        service.saveWind(wind);
    }
}
