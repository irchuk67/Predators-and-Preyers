package managers;

import cells.Animal;
import cells.Food;
import userInterface.Console;
import visualComponents.Map;
import visualComponents.Statistics;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private Console console = new Console();
    private AnimalManager animalManager = new AnimalManager();

    public void moveMap(Map map) {
        List<Animal> toRemovePray = new ArrayList<>();
        List<Food> toRemoveFood = new ArrayList<>();
        for (Animal animal : map.getAnimals()) {
            animalManager.moveAnimal(animal, map, toRemovePray, toRemoveFood);
        }
        Statistics statistics = Statistics.getInstance();
        statistics.addDeadPray(toRemovePray.size());
        map.getAnimals().removeAll(toRemovePray);
        statistics.addEatenFood(toRemoveFood.toArray().length);
        map.getGrass().removeAll(toRemoveFood);
    }
}
