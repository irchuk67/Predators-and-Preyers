package managers;

import cells.*;
import visualComponents.Map;

public class CellFactory {

    public Cell createPray(int type) {
        if (type == 1) {
            return new Deer();
        }
        else if(type == 2){
            return new Hare();
        }
        else if(type == 3){
            return new Sheep();
        }
        return new Pray();
    }

    public Predator createPredator(int type) {
        if (type == 1) {
            return new Fox();
        }
        else if(type == 2){
            return new Lynx();
        }
        else if(type == 3){
            return new Wolf();
        }
        return new Predator();
    }

    public Food createFood() {
        return new Food();
    }

    public void makeEmptyCell(Cell cell) throws Exception {
        if (cell instanceof Food) {
            cell = null;
        }
        if (cell instanceof Animal){
            cell = null;
            System.out.println();
        } else{
            throw new Exception("Not an object!");
        }
    }

//    public void makeZeroLiveDurationNull(Map map) {
//        for (int i = 0; i < map.getGrass().size(); i++) {
//            if (map.getGrass().get(i).getLiveDuration() <= 0) {
//                try {
//                    makeEmptyCell(map.getGrass().get(i));
//                    System.out.println("/make grass null");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//        for (int i = 0; i < map.getAnimals().size(); i++) {
//            if (map.getAnimals().get(i).getLiveDuration() <= 0) {
//                try {
//                    makeEmptyCell(map.getAnimals().get(i));
//                    System.out.println("/make animal null");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//    }
}