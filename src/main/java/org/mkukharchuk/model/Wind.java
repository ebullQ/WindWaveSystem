package org.mkukharchuk.model;

import javax.persistence.*;

@Entity
public class Wind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double speed;
    private int direction;
    @OneToOne(mappedBy = "wind")
    private Image image;

    public Wind() {
    }

    public Wind(double speed, int direction) {
        this.speed = speed;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getDirectionImageName(){
        if(direction>=23 && direction<68){
            return "SW.png";
        }else if(direction>=68 && direction<113){
            return "W.png";
        }else if(direction>=113 && direction<158){
            return "NW.png";
        }else if(direction>=158 && direction<203){
            return "N.png";
        }else if(direction>=203 && direction<248){
            return "NE.png";
        }else if(direction>=248 && direction<293){
            return "E.png";
        } else if(direction>=293 && direction<338){
            return "SE.png";
        }else {
            return "S.png";
        }
    }

    public String getDirectionName(){
        if(direction>=23 && direction<68){
            return "юго-западное";
        }else if(direction>=68 && direction<113){
            return "западное";
        }else if(direction>=113 && direction<158){
            return "северо-западное";
        }else if(direction>=158 && direction<203){
            return "северное";
        }else if(direction>=203 && direction<248){
            return "северо-восточное";
        }else if(direction>=248 && direction<293){
            return "восточное";
        } else if(direction>=293 && direction<338){
            return "юго-восточное";
        }else {
            return "южное";
        }
    }

    @Override
    public String toString() {
        return speed + " " + direction;
    }
}
