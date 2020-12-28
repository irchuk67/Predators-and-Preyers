package userInterface;

import cells.*;
import visualComponents.Map;
import visualComponents.Statistics;

import java.util.Scanner;

public class Console {
    private final String RESET = "\033[0m";  // Text Reset
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK

    public void startText() {
        System.out.println("Hello!\n" +
                "It`s our 'Predators and Prays' simulator.\n\n" +
                "Enter the size of game field(matrix NxN): ");
    }

    public int mapSizeEnter() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public int prayOnMap() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1. Deer\n2. Hare\n3. Sheep\nChoose a type of pray: ");
        return scanner.nextInt();
    }

    public int predatorOnMap() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n1. Fox\n2. Lynx\n3. Wolf\nChoose a type of predator: ");
        return scanner.nextInt();
    }

    public void printMap(Map map) {
        System.out.println("Current map: \n");
        for (int i = 0; i < map.getMapSize(); i++) {
            for (int j = 0; j < map.getMapSize(); j++) {
                if (map.getMap()[i][j] instanceof Food) {
                    System.out.print(BLACK_BRIGHT + GREEN_BACKGROUND + "\t" + map.getMap()[i][j].getLiveDuration() + "\t" + RESET);
                } else if (map.getMap()[i][j] instanceof Deer) {
                    System.out.print(BLACK_BRIGHT + RED_BACKGROUND + "\t" + map.getMap()[i][j].getLiveDuration() + "\t" + RESET);
                } else if (map.getMap()[i][j] instanceof Hare) {
                    System.out.print(BLACK_BRIGHT + BLUE_BACKGROUND + "\t" + map.getMap()[i][j].getLiveDuration() + "\t" + RESET);
                } else if (map.getMap()[i][j] instanceof Sheep) {
                    System.out.print(BLACK_BRIGHT + CYAN_BACKGROUND + "\t" + map.getMap()[i][j].getLiveDuration() + "\t" + RESET);
                } else if (map.getMap()[i][j] instanceof Fox) {
                    System.out.print(BLACK_BRIGHT + YELLOW_BACKGROUND + "\t" + map.getMap()[i][j].getLiveDuration() + "\t" + RESET);
                } else if (map.getMap()[i][j] instanceof Lynx) {
                    System.out.print(BLACK_BRIGHT + PURPLE_BACKGROUND + "\t" + map.getMap()[i][j].getLiveDuration() + "\t" + RESET);
                } else if (map.getMap()[i][j] instanceof Wolf) {
                    System.out.print(BLACK_BRIGHT + BLACK_BACKGROUND + "\t" + map.getMap()[i][j].getLiveDuration() + "\t" + RESET);
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

    public void printStatistics(){
        Statistics statistics = Statistics.getInstance();
        System.out.println("Food: " + statistics.getFood());
        System.out.println("Pray: " + statistics.getPreys());
        System.out.println("Predator: " + statistics.getPredators());
        System.out.println("Dead prays: " + statistics.getDeadPray());
        System.out.println("Eaten food: " + statistics.getEatenFood());
    }

    public void clean() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public void endSimulation() {
        System.out.println("\nEnd of the simulation");
    }
}