package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.view.GameView;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Enumeration;

public class Map {


    private int[] mapx = new int[8];
    private int[] mapy = new int[8];
    private int nbrBuoys = 0;

    public Map(int numMap) {
        String [] line;
        //File file = new File(String.valueOf(GameView.class.getResource("map.txt")));
        File file ;

        //File file = new File("src/main/resources/fr/ensicaen/genielogiciel/mvp/view/map.txt");
        switch (numMap){
            case 1 :
                file = new File("src/main/resources/fr/ensicaen/genielogiciel/mvp/view/map.txt");
                break;
            case 2 :
                file = new File("src/main/resources/fr/ensicaen/genielogiciel/mvp/view/map2.txt");
                break;
            case 3 :
                file = new File("src/main/resources/fr/ensicaen/genielogiciel/mvp/view/map3.txt");
                break;
            default:
                file = new File("src/main/resources/fr/ensicaen/genielogiciel/mvp/view/map.txt");
                break;

        }
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                line = reader.readLine().split("");
                nbrBuoys = Integer.parseInt(line[0]);
                for (int i = 0; i< nbrBuoys; i++) {
                    line = reader.readLine().split(":");
                    mapx[i] = Integer.parseInt(line[0]);
                    mapy[i] = Integer.parseInt(line[1]);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }



    }

    public int[] getMapx() {
        return mapx;

    }

    public int[] getMapy() {
        return mapy;
    }

    public int getNbrBuoys() {
        return nbrBuoys;
    }

    public int[] getDepArr8(){
        int [] PointDepArr = new int[2];
        PointDepArr[0] = mapx[0];
        PointDepArr[1] = mapy[0];
        return PointDepArr;

    }
}
