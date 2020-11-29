package managers;

import cells.Animal;
import cells.Food;
import cells.Pray;
import cells.Predator;
import visualComponents.Map;

import java.util.List;

public class AnimalManager {
    private MapManager mapManager = new MapManager();
    private CellManager cellManager = new CellManager();

    public void moveAnimal(Animal animal, Map map, List<Animal> toRemovePray, List<Food> toRemoveFood){
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
                        toRemoveFood.add((Food) animal.getAim());
                    }
                    if(animal instanceof Predator){
                        toRemovePray.add((Animal) animal.getAim());
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
