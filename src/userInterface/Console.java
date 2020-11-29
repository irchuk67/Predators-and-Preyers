package userInterface;

import cells.Food;
import cells.Pray;
import cells.Predator;
import visualComponents.Map;

import java.util.Scanner;

public class Console {
    private final String RED_BACKGROUND = "\033[41m";    // RED
    private final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    private final String WHITE_BACKGROUND = "\033[47m";  // WHITE
    private final String RESET = "\033[0m";  // Text Reset
    private final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    private final String BLACK_BRIGHT = "\033[0;90m";  // BLACK

    public void startText() {
        System.out.println("Hello!\n" +
                "It`s our 'Predators and Prays' simulator.\n\n" +
                "Enter the size of game field(matrix NxN): ");
    }

    public int mapSizeEnter() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void printMap(Map map) {
        System.out.println("Current map: \n");
        for (int i = 0; i < map.getMapSize(); i++) {
            for (int j = 0; j < map.getMapSize(); j++) {
                if (map.getMap()[i][j] instanceof Food) {

                    System.out.print(BLACK_BRIGHT + GREEN_BACKGROUND + "\t" + map.getMap()[i][j].getLiveDuration() + "\t" + RESET);
                } else if (map.getMap()[i][j] instanceof Pray) {
                    System.out.print(BLACK_BRIGHT + CYAN_BACKGROUND + "\t" + map.getMap()[i][j].getLiveDuration() + "\t" + RESET);
                } else if (map.getMap()[i][j] instanceof Predator) {
                    System.out.print(BLACK_BRIGHT + RED_BACKGROUND + "\t" + map.getMap()[i][j].getLiveDuration() + "\t" + RESET);
                } else {
                    System.out.print(BLACK_BRIGHT + WHITE_BACKGROUND + "\t0\t" + RESET);
                }
            }
            System.out.println();
        }
    }

    public void printStatus(Map map) {
        System.out.println("Food: " + map.getMaxFood());
        System.out.println("Prays: " + map.getMaxPrays());
        System.out.println("Predators: " + map.getMaxPredators());
    }

    public void clean() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}