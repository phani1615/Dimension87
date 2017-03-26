package model;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import shared.Person;
import shared.Tile;
import javafx.scene.image.Image;
import Controller.*;

/**
 * Created by phani on 3/25/2017.
 */
public class Model implements InterfaceModel {


    Tile map [][];
    Person team1[];
    Person team2[];
    ArrayList<Image> spriteData;
    Dimension87_Controller controller;
    File f1;

    public Model(Dimension87_Controller controller) {

        //this.controller = controller;
        //load(f);
        spriteData = new ArrayList<Image>();
        this.controller = controller;
    }

    public void loadMap(File f){
        /*load(f);
        try {
            for (int i = 0; i <  ; i++) {

            }
        }*/
        int i = 0;
        int j = 0;
        try {
            Scanner in = new Scanner(f);
//            String ls = System.getProperty("line.separator");
//            String lines = "";
            int xsize = in.nextInt();
            int ysize = in.nextInt();
            System.out.println(xsize + ", " + ysize);
            map = new Tile[ysize][xsize];
            while (in.hasNext()) {
//            	System.out.println(i + ", " + j);
                String imageName = in.next();
                String walkable = in.next();
                imageName = imageName.substring(1);
                walkable = walkable.substring(0,walkable.length()-1);
                Image image = new Image("file" +imageName);
                Tile tile;
                if(walkable.equals("true")){
                    tile = new Tile(true,image);
                }else{
                    tile = new Tile(false,image);
                }
                map[j][i] = tile;
                i++;
                if (i >= xsize){
                    i =0;
                    j++;
                }
                if(j >= ysize){
                    break;
                }
                //map[j][i] = tile;
            }
            in.close();
        }catch (Exception e){
            System.out.println(e.toString() + f.toString());
        }
    }
    public void setMapVal (Tile tile, int x, int y) {
        map[y][x] = tile;
    }

    public Tile getTileData(int row, int col) {
        return map[row][col];
    }

    @Override
    public Person[] getTeam1() {
        return team1;
    }

    @Override
    public Person[] getTeam2() {
        return team2;
    }

    public void setPlayerSpot(Person person, int newX, int newY){
        Tile tile = getMapVal(newX,newY);
        tile.setPlayer(person);
        map[newX][newY] =tile;
    }
    public void removePlayerSpot(int x, int y){
        Tile tile = getMapVal(x,y);
        tile.removePlayer();
        map[x][y] =tile;

    }

    public Tile getMapVal(int x, int y){
        return map[x][y];
    }

    public Boolean inBounds(int x, int y){
        System.out.println("(" + y + ", " + x + ")");
        if (x >= 0 && x < map[0].length-1 && y >= 0 && y < map.length-1){
            if (map[y][x].isWalkable()){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public int getNumCols(){
        return map[0].length;
    }
    public int getNumRows(){
        return map.length;
    }

}
