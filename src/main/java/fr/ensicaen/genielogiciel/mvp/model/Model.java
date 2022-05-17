package fr.ensicaen.genielogiciel.mvp.model;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.swing.*;

public class Model {
    final private String _nickname;
    private double x; // x position of the boat
    private double y; // y position of the boat
    final private double[] direction = new double[2];
    private int nbrBuoysPassed = 0;
    private String _windSpeed;
    private int speedWind;
    private String _directionWind;
    final private double[] windDirection = new double[2];
    final private Map map;
    private boolean arrived = false;


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isArrived() {
        return arrived;
    }

    public String getNickname() {
        return _nickname;
    }

    public Model(String _nickname, double x, double y, Map map) {
        this._nickname = _nickname;
        this.x = x;
        this.y = y;
        this.setWindSpeed();
        this.setDirectionWind();
        this.setWindDirection();
        this.map = map;
    }

    public double turnRight(double val) {
        x += val * (direction[0] * Math.cos(3.14/2) - direction[1]*Math.sin(3.14/2));
        y += val * (direction[0] * Math.sin(3.14/2) + direction[1]*Math.cos(3.14/2));
        return 0;
    }

    public double turnLeft(double val) {
        turnRight(val * -1);
        return 0;
    }

    public double moveForward(double val) {
        setDirection();
        x += val*direction[0];
        y += val*direction[1];
        System.out.println(speedWind );
        System.out.println(_directionWind);
        System.out.println(windDirection[0]);
        System.out.println(windDirection[1]);
        x += speedWind*windDirection[0]/3;
        y += speedWind*windDirection[1]/3;
        return 0;
    }

    public double moveBack(double val) {
        moveForward(val*-1);
        return 0;
    }

    public String getWindSpeed(){
        return _windSpeed;
    }

    public String getWindDirection(){
        return _directionWind;
    }

    public void setWindSpeed(){
        _windSpeed = new Weather("https://www.prevision-meteo.ch/services/json/lat=49.283lng=0.25").get_windSpeed();
        speedWind = Integer.parseInt(_windSpeed);
    }
    public void setDirectionWind() {
        _directionWind = new Weather("https://www.prevision-meteo.ch/services/json/lat=49.283lng=0.25").get_windDirectory();
    }

    public void setWindDirection() {
        switch (_directionWind) {
            case "N":
                windDirection[0] = 0;
                windDirection[1] = -1;
                break;
            case "O":
                windDirection[0] = 1;
                windDirection[1] = 0;
                break;
            case "S" :
                windDirection[0] = 0;
                windDirection[1] = 1;
                break;
            case "E":
                windDirection[0] = -1;
                windDirection[1] = 0;
                break;
            case "SO":
                windDirection[0] = 1 / (float) 2;
                windDirection[1] = 1 / (float) 2;
                break;
            case "NO":
                windDirection[0] = 1 / (float) 2;
                windDirection[1] = -1 / (float) 2;
                break;
            case "SE":
                windDirection[0] = -1 / (float) 2;
                windDirection[1] = 1 / (float) 2;
                break;
            case "NE" :
                windDirection[0] = -1 / (float) 2;
                windDirection[1] = -1 / (float) 2;
                break;
            default:
                windDirection[0] = 0;
                windDirection[1] = 0;
                break;
        }
    }

    public void setDirection() {
        double depX;
        double depY;
        double norm;
        if (nbrBuoysPassed == map.getNbrBuoys() ) {
            depX = 0;
            depY = 0;
            this.direction[0] = 0;
            this.direction[1] = 0;
        } else {
            depX = map.getMapx()[nbrBuoysPassed] - x;
            depY = map.getMapy()[nbrBuoysPassed] - y;
            norm = Math.sqrt(Math.pow(depX,2) + Math.pow(depY,2));
            if (norm != 0) {
                this.direction[0] = depX / norm;
                this.direction[1] = depY / norm;
            }

        }
        if (Math.abs(depX) < 15 && Math.abs(depY) < 15) {
            if (nbrBuoysPassed == map.getNbrBuoys()) {
                arrived = true;
                nbrBuoysPassed--;
            }
            nbrBuoysPassed++;
        }
    }
}
