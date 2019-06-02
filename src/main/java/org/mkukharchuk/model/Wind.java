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
        if(direction<=45){
            return "SE.png";
        }else if(direction<=90){
            return "E.png";
        }else if(direction<=135){
            return "NE.png";
        }else if(direction<=180){
            return "N.png";
        }else if(direction<=225){
            return "NW.png";
        }else if(direction<=270){
            return "W.png";
        } else if(direction<=315){
            return "SW.png";
        }else{
            return "S.png";
        }
    }

    @Override
    public String toString() {
        return speed + " " + direction;
    }
}
