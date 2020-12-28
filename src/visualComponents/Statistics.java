package visualComponents;
import cells.Animal;
import cells.Pray;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private static Statistics instance;
    private Integer food = 0;
    private Integer preys = 0;
    private Integer predators = 0;
    private Integer deadPray = 0;
    private Integer eatenFood = 0;
    private Integer deadPredators = 0;

    private Statistics(){}

    public void count(Map map){
        food = 0;
        preys = 0;
        predators = 0;
        for (int i = 0; i < map.getAnimals().toArray().length; i++){
            if (map.getAnimals().get(i) instanceof Pray){
                preys++;
            }
            else {
                predators++;
            }
        }
        food = map.getGrass().toArray().length;
    }

    public Integer getFood() {
        return food;
    }

    public Integer getPreys() {
        return preys;
    }


    public Integer getDeadPray() {
        return deadPray;
    }

    public Integer getEatenFood() {
        return eatenFood;
    }

    public void addDeadPray(int deadCount){
        deadPray += deadCount;
    }

    public void addEatenFood(int eatenFood){
        this.eatenFood += eatenFood;
    }

    public Integer getPredators() {
        return predators;
    }

    public static Statistics getInstance(){
        if (instance == null){
            instance = new Statistics();
        }
        return instance;
    }
}