package sample;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Terrain.
 */
public class Terrain {

    private static ArrayList<Object> map;

    /**
     * Instantiates a new Terrain.
     */
    public Terrain(){
        map = new ArrayList<Object>(landingSpot());
    }

    /**
     * Method landingSpot() creates a new landing spot.
     * this method will create the 2D array with random numbers and the square of
     * 9 zeroes. Then it stores the coordinate of the middle zero on locationX and locationY and
     * also stores the random 2D array. It stores all of this in an arrayList.
     */
    private static ArrayList<Object> landingSpot() {
        Random rand = new Random();
        int maxRan = 50;
        int x = 0;
        while(x < 20){
            x = rand.nextInt(maxRan);
        }
        int y = x;
        int[][] landingTerrain = new int[y][x];
        Random ran = new Random();
        int min = 1;
        int max = 9;
        int placementX = ran.nextInt(x);
        int placementY = placementX;

        if (placementX == 0 || placementY == 0 || placementX == maxRan || placementY == maxRan ) {
            placementX = 5;
            placementY = 5;
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                landingTerrain[i][j] = (int) Math.floor(Math.random() * (max - min + 1) + min);
            }
        }

        int counter = 0;
        int locationX = 0;
        int locationY = 0;
        while (counter < 3) {
            landingTerrain[placementY][placementX] = 0;
            landingTerrain[placementY][placementX+1] = 0;
            landingTerrain[placementY][placementX-1] = 0;

            landingTerrain[placementY+1][placementX] = 0;
            landingTerrain[placementY+1][placementX+1] = 0;
            landingTerrain[placementY+1][placementX-1] = 0;

            landingTerrain[placementY-1][placementX] = 0;
            landingTerrain[placementY-1][placementX+1] = 0;
            landingTerrain[placementY-1][placementX-1] = 0;

            if(counter == 2){
                locationY = placementY;
                locationX = placementX;
            }
            counter++;
        }

        ArrayList<Object> keyInfo = new ArrayList<>();
        keyInfo.add(landingTerrain);
        keyInfo.add(locationX);
        keyInfo.add(locationY);

        return keyInfo;
    }

    /**
     * Landing terrain int [ ] [ ].
     * This method when called will retun the 2D Array.
     * @return the int [ ] [ ]
     */
    public static int[][] landingTerrain(){
        new Terrain();
        int[][] landingTerrain = (int[][]) map.get(0);
        return landingTerrain;
    }

    /**
     * Location array list.
     * This method when called willl return the coordinates of the zero stored beforehand.
     * @return the array list
     */
    public static ArrayList<Object> location(){
        int locationX = (int) map.get(1);
        int locationY = (int) map.get(2);
        ArrayList<Object> locations = new ArrayList<Object>();
        locations.add(locationX);
        locations.add(locationY);
        return locations;
    }
}