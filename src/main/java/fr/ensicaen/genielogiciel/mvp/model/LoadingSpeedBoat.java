package fr.ensicaen.genielogiciel.mvp.model;

import com.google.gson.internal.bind.util.ISO8601Utils;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class LoadingSpeedBoat  {
    private int _angle;
    private double _speedBoat;
    private int _nb_team;
    private int  _tailleVoilier;
    public LoadingSpeedBoat(int angle,int speed) throws IOException {
        _angle=angle;
        get_speed_boat(angle,speed,_nb_team,_tailleVoilier);
    }
    public double get_speed_boat(int angle,int speed,int _nb_team,int _tailleVoilier) throws IOException {
        double c=0.1*_nb_team-0.002*_tailleVoilier;
        String[] line;
        int indexSpeedWind=0;
        try {
            BufferedReader fileSpeed = new BufferedReader(new FileReader("src/main/resources/Vitesse/polaire-figaro-pol.txt"));
            line = fileSpeed.readLine().split("[\t| ]");
            for (int i = 0; i < line.length; i++) {
                if (line[i] != "") {
                    if (speed > 30 && angle == 30) {
                        return 4.6-c;
                    } else if (speed > 30 && angle == 90) {
                    return 9.3-c;
                }else if ((speed < 4) || (speed > 30 && angle < 30)) {
                return 1.3-c;
                }
                    }
                     else if (speed > 30 && angle > 30) {
                        return 12.50;
                    }
                    int a = Integer.parseInt(line[i]);
                    if ((a == speed - 1) || (a == speed) || (a == speed + 1)) {
                        break;
                    }
                    indexSpeedWind++;
                }

            while(line !=null ) {
                line = fileSpeed.readLine().split("[\t| ]");
                if (line[0] != "") {
                    if (Integer.parseInt(line[0]) == angle) {
                        System.out.println("They match!!!!!!");
                        _speedBoat = Double.parseDouble(line[indexSpeedWind+1]);
                        return _speedBoat-c;
                    }
                }
            }
        } catch ( NumberFormatException e) {
            e.printStackTrace();
            System.out.println("erreur");
        }
       return 0;
    }

    public double get_speed() {
        return _speedBoat;
    }
}

