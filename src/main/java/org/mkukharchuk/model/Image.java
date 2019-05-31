package org.mkukharchuk.model;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fileName;
    private String date;
    private int time;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "wind_id", referencedColumnName = "id")
    private Wind wind;

    public Image() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public Wind getWind() {
        return wind;
    }
    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
