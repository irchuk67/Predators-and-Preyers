package managers;

import cells.Animal;
import cells.Food;
import cells.Pray;
import cells.Predator;
import visualComponents.Map;

import java.util.List;

public class AnimalManager {
    private MapManager mapManager = new MapManager();
    private CellFactory cellFactory = new CellFactory();

    public void moveAnimal(Animal animal, Map map, List<Animal> toRemovePray) {
        if (!animal.isTracking()) {
            animal.setAim(animal.searchFood(map));
            animal.setTracking(true);
        }
        if (animal.getAim() == null) {
            animal.setTracking(false);
            animal.setAim(animal.searchFood(map));
            animal.setTracking(true);
        }
        if (animal.getAim() != null) {
            animal.move(animal.getAim());
            if (animal.getI() == animal.getAim().getI() && animal.getJ() == animal.getAim().getJ()) {
                try {
                    if (animal instanceof Pray && animal.getAim() instanceof Food) {
//                        cellFactory.makeEmptyCell(animal.getAim());
                        mapManager.removeFoodFromList(animal.getAim(), map);
                        animal.eat();
                        animal.setTracking(false);
                    } else if (animal instanceof Predator && animal.getAim() instanceof Pray) {
//                        cellFactory.makeEmptyCell(animal.getAim());
                        toRemovePray.add((Animal) animal.getAim());
                        animal.eat();
                        animal.setTracking(false);
                    }

                } catch (Exception exc) {
                    System.out.println("Can`t");
                }
            }
        }
    }
}