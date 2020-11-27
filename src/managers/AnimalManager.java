package managers;

import cells.Animal;
import cells.Cell;
import visualComponents.Map;

public class AnimalManager {
    private MapManager mapManager = new MapManager();

    public void moveAnimal(Animal animal, Map map){
        Cell food = animal.searchFood(map);
        animal.move(food);
    }
}
