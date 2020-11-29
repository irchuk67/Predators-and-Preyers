import cells.Food;
import cells.Pray;
import managers.Controller;

public class Main {
    public static void main(String[] args) {
        //Controller controller = new Controller();
        //controller.startSimulation();
        Food food = new Food(6);
        Pray pray = new Pray(10, 3);
        food.setCoordinates(5,9);
        pray.setCoordinates(13, 9);
        while (food.getI() != pray.getI() || food.getJ() != pray.getJ()){
            System.out.print(pray.getI() + ",");
            System.out.println(pray.getJ());
            pray.move(food);
        }
        System.out.print(pray.getI() + ",");
        System.out.println(pray.getJ());
    }
}
