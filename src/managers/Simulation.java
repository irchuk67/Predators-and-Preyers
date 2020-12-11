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
        int animalsBefore = map.getAnimals().size();
        int foodBefore = map.getGrass().size();
        for (Animal animal : map.getAnimals()) {
            animalManager.moveAnimal(animal, map, toRemovePray);
        }
        Statistics statistics = Statistics.getInstance();
        map.getAnimals().removeAll(toRemovePray);
        statistics.addDeadPray(animalsBefore - map.getAnimals().size());
        statistics.addEatenFood(foodBefore - map.getGrass().size());
    }

    public void reduceLiveDurationMap(Map map) {
        for(int i = 0 ; i < map.getGrass().size(); i++) {
            map.getGrass().get(i).reduceLiveDuration();
        }

        for(int i = 0 ; i < map.getAnimals().size(); i++) {
            map.getAnimals().get(i).reduceLiveDuration();
        }
    }
}