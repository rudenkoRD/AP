package lab3;

import lab3.arena.Arena;
import lab3.arena.OneToOneArena;
import lab3.arena.TeamArena;
import lab3.droid.*;
import lab3.writer.ArenaIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Lab3 {
    DroidsManager manager = new DroidsManager();

    public static void main(String[] args) {
        new Lab3().run();
    }

    void run() {
        Scanner input = new Scanner(System.in);

        while (true) {
            printMainMenu();

            int menuItem = input.nextInt();
            boolean exit = false;

            switch (menuItem) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showAllDroids();
                    break;
                case 3:
                    playBattleFromFile();
                    break;
                case 4:
                    runOneToOneBattle();
                    break;
                case 5:
                    runTeamBattle();
                    break;
                case 6:
                    exit = true;
                    break;
            }

            if (exit) break;
        }
    }

    void createDroid() {
        printDroidMenu();
        System.out.println("Enter droid type number: ");
        int type = new Scanner(System.in).nextInt();
        switch (type) {
            case 1:
                manager.addDroid(new Attacker());
                break;
            case 2:
                manager.addDroid(new Defender());
                break;
            case 3:
                manager.addDroid(new Hacker());
                break;
            default:
                System.out.println("No such droid type!");
        }

    }

    void showAllDroids() {
        manager.showAllDroids();
        System.out.println("press enter to continue..");
        new Scanner(System.in).nextLine();
    }

    void playBattleFromFile() {
        System.out.println("Enter file path: ");
        String filePath = new Scanner(System.in).nextLine();
        try {
            Arena arena = ArenaIO.read(filePath);

            arena.simulateBattle(false);
        } catch (Exception e) {
            System.out.println("File error!");
        }
    }

    void runOneToOneBattle() {
        manager.showAttackerDroids();
        System.out.println("Enter two droid numbers to battle: ");
        Scanner in = new Scanner(System.in);
        int first = in.nextInt();
        int second = in.nextInt();

        if (first == second) {
            System.out.println("Droids can't be the same");
            return;
        }

        Droid[] droids = manager.getAttackDroids();

        try {
            Arena arena = new OneToOneArena(droids[first - 1].copy(), droids[second - 1].copy());

            System.out.println("Record battle?(y/n)");
            String record = in.next();
            arena.simulateBattle(record.equalsIgnoreCase("y"));
        } catch (Exception e) {
            System.out.println("Invalid data!");
        }
    }

    void runTeamBattle() {
        manager.showAllDroids();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter first team numbers: ");
        int[] firstTeamIndices = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println("Enter second team numbers:");
        int[] secondTeamIndices = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Droid> firstTeam = new ArrayList<>();
        List<Droid> secondTeam = new ArrayList<>();

        try {
            for (int i : firstTeamIndices) {
                firstTeam.add(manager.getAllDroids().get(i - 1).copy());
            }

            for (int i : secondTeamIndices) {
                secondTeam.add(manager.getAllDroids().get(i - 1).copy());
            }

            Arena arena = new TeamArena(firstTeam, secondTeam);

            System.out.println("Record battle?(y/n)");
            String record = in.next();
            arena.simulateBattle(record.equalsIgnoreCase("y"));
        } catch (Exception e) {
            System.out.println("Invalid data!");
        }
    }

    private void printDroidMenu() {
        System.out.println(" 1. Attacker\n 2. Defender\n 3. Hacker\n");
    }

    private void printMainMenu() {
        System.out.print("===========Menu===========\n" +
                "1. Create droid\n" +
                "2. Show droids\n" +
                "3. Play battle from file\n" +
                "4. Run 1 vs 1 battle\n" +
                "5. Run team battle\n" +
                "6. Exit\n");
    }
}
