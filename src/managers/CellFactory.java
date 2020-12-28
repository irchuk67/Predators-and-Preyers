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

    public Cell createPray(String type) { //overloaded for GUI
        System.out.println("Create pray: " + type);
        return switch (type) {
            case "Deer" -> new Deer();
            case "Hare" -> new Hare();
            case "Sheep" -> new Sheep();
            default -> new Pray();
        };
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

    public Predator createPredator(String type) { //overloaded for GUI
        System.out.println("Create predator: " + type);
        return switch (type) {
            case "Fox" -> new Fox();
            case "Lynx" -> new Lynx();
            case "Wolf" -> new Wolf();
            default -> new Predator();
        };
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
}