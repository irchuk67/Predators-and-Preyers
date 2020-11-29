package managers;

import cells.*;

public class CellManager {  //TODO: change to factory method

    public Pray createPray(int liveDuration, int reproductionCounter){
        return new Pray(liveDuration, reproductionCounter);
    }

    public Predator createPredator(int liveDuration, int reproductionCounter){
        return new Predator(liveDuration, reproductionCounter);
    }

    public Food createFood(int liveDuration){
        return new Food(liveDuration);
    }

    public void makeEmptyCell(Cell cell) throws Exception {
        if(cell instanceof Animal || cell instanceof Food){
            cell = null;
        }
        else{
            throw new Exception("Not an object!");
        }
    }
}