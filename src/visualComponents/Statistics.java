package visualComponents;
import cells.Animal;
import cells.Pray;
import visualComponents.Map;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private static Statistics instance;
    private int food = 0;
    private int pray = 0;
    private int predator = 0;
    private int deadPray = 0;
    private int eatenFood = 0;
    private int deadPredator = 0;

    private Statistics(){}

    public void count(Map map){
        food = 0;
        pray = 0;
        predator = 0;
        for (int i = 0; i < map.getAnimals().toArray().length; i++){
            if (map.getAnimals().get(i) instanceof Pray){
                pray++;
            }
            else {
                predator++;
            }
        }
        food = map.getGrass().toArray().length;
    }

    public int[] prevStep(int[] statistics){
        int[] prev = new int[3];
        for (int i = 0; i < 3; i++){
            prev[i] = statistics[i];
        }
        return prev;
    }

    public int getFood() {
        return food;
    }

    public int getPray() {
        return pray;
    }

    public int getPredator() {
        return predator;
    }

    public int getDeadPray() {
        return deadPray;
    }

    public int getEatenFood() {
        return eatenFood;
    }

    public int getDeadPredator() {
        return deadPredator;
    }

    public void addDeadPray(int deadCount){
        deadPray += deadCount;
    }

    public void addEatenFood(int eatenFood){
        this.eatenFood += eatenFood;
    }

    public void addDeadPredator(int deadPredator){
        this.deadPredator += deadPredator;
    }

    public static Statistics getInstance(){
        if (instance == null){
            instance = new Statistics();
        }
        return instance;
    }
}
