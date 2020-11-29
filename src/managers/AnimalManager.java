package managers;

import cells.Animal;
import cells.Pray;
import cells.Predator;
import visualComponents.Map;

import java.util.List;

public class AnimalManager {
    private MapManager mapManager = new MapManager();
    private CellManager cellManager = new CellManager();

    public void moveAnimal(Animal animal, Map map, List<Animal> toRemove){
        if(!animal.isTracking()){
            animal.setAim(animal.searchFood(map));
            animal.setTracking(true);
        }
        if(animal.getAim() == null){
            animal.setTracking(false);
            animal.setAim(animal.searchFood(map));
            animal.setTracking(true);
        }
        if(animal.getAim() != null){
            animal.move(animal.getAim());
            if(animal.getI() == animal.getAim().getI() && animal.getJ() == animal.getAim().getJ()) {
                try{
                    cellManager.makeEmptyCell(animal.getAim());
                    if(animal instanceof Pray){
                        mapManager.removeFoodFromList(animal.getAim(), map);
                    }
                    if(animal instanceof Predator){
                        toRemove.add((Animal) animal.getAim());
                    }

                    animal.eat();
                    animal.setTracking(false);
                } catch (Exception exc) {
                    System.out.println("Can`t");
                }
            }
        }
    }
}
