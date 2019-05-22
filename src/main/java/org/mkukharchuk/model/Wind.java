package org.mkukharchuk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double speed;
    private int direction;

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

    @Override
    public String toString() {
        return speed + " " + direction;
    }
}
