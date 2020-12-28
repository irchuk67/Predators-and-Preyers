package visualComponents;
import cells.Animal;
import cells.Pray;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private static Statistics instance;
    private Integer startFood = 0;
    private Integer startPreys = 0;
    private Integer startPredators = 0;
    private Integer food = 0;
    private Integer preys = 0;
    private Integer predators = 0;
    private Integer deadPray = 0;
    private Integer eatenFood = 0;
    private Integer deadPredators = 0;
    private Integer bornFood = 0;
    private Integer bornPreys = 0;
    private Integer bornPredators = 0;
    private List<Integer> amountAnimalList = new ArrayList<>();
    private String prey;
    private String predator;

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

    public Integer getPredators() {
        return predators;
    }

    public Integer getDeadPray() {
        return deadPray;
    }

    public Integer getEatenFood() {
        return eatenFood;
    }

    public Integer getDeadPredators() {
        return deadPredators;
    }

    public Integer getBornFood() {
        return bornFood;
    }

    public Integer getBornPreys() {
        return bornPreys;
    }

    public Integer getBornPredators() {
        return bornPredators;
    }

    public void addDeadPray(int deadCount){
        deadPray += deadCount;
    }

    public void addEatenFood(int eatenFood){
        this.eatenFood += eatenFood;
    }

    public void addDeadPredators(int deadPredator){
        this.deadPredators += deadPredator;
    }

    public void addBornFood() {
        bornFood++;
    }

    public void addBornPrey() {
        bornPreys++;
    }

    public void addBornPredators() {
        bornPredators++;
    }

    public void setAmountInList(List<Animal> animals) {
        amountAnimalList.add(animals.size());
    }

    public List<Integer> getAmountAnimalList() {
        return amountAnimalList;
    }

    public Integer getStartFood() {
        return startFood;
    }

    public Integer getStartPreys() {
        return startPreys;
    }

    public Integer getStartPredators() {
        return startPredators;
    }

    public void addStartFood() {
        startFood++;
    }

    public void addStartPreys() {
        startPreys++;
    }

    public void addStartPredators() {
        this.startPredators++;
    }

    public String getPrey() {
        return prey;
    }

    public void setPrey(String prey) {
        this.prey = prey;
    }

    public String getPredator() {
        return predator;
    }

    public void setPredator(String predator) {
        this.predator = predator;
    }

    public static Statistics getInstance(){
        if (instance == null){
            instance = new Statistics();
        }
        return instance;
    }
}

