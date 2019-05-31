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
            return "SV.png";
        }else if(direction<=90){
            return "V.png";
        }else if(direction<=135){
            return "UV.png";
        }else if(direction<=180){
            return "U.png";
        }else if(direction<=225){
            return "UZ.png";
        }else if(direction<=270){
            return "Z.png";
        } else if(direction<=315){
            return "SZ.png";
        }else{
            return "S.png";
        }
    }

    @Override
    public String toString() {
        return speed + " " + direction;
    }
}
