package managers;

import cells.Animal;
import userInterface.Console;
import visualComponents.Map;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private Console console = new Console();
    private AnimalManager animalManager = new AnimalManager();

    public void moveMap(Map map) {
        List<Animal> toRemove = new ArrayList<>();
        for (Animal animal : map.getAnimals()) {
            animalManager.moveAnimal(animal, map, toRemove);
        }
        map.getAnimals().removeAll(toRemove);
    }
}
