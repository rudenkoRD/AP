package lab3.arena;

import lab3.droid.Droid;
import lab3.output.Output;
import lab3.writer.ArenaIO;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class TeamArena implements Arena, Serializable {
    final Random random;
    final List<Droid> firstTeam;
    final List<Droid> secondTeam;

    public TeamArena(List<Droid> firstTeam, List<Droid> secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        random = new Random();
    }

    @Override
    public void simulateBattle(boolean record) {
        if (record) ArenaIO.write(this, "record.obj");

        int roundCount = 0;
        while (!firstTeam.isEmpty() && !secondTeam.isEmpty()) {
            System.out.println("First team attacks: ");
            teamAttack(firstTeam, secondTeam);
            System.out.println();

            if (secondTeam.isEmpty()) break;


            System.out.print(Output.ANSI_BLACK_BACKGROUND);
            System.out.println("Second team attacks: ");
            teamAttack(secondTeam, firstTeam);
            System.out.print(Output.ANSI_RESET + "\n");

            roundCount++;
            if (roundCount > 100) {
                break;
            }
        }

        printResults();
    }

    private void teamAttack(List<Droid> attackers, List<Droid> defenders) {
        for (int i = 0; i < attackers.size(); i++) {
            int target = random.nextInt(defenders.size());

            defenders.get(target).receiveAttack(attackers.get(i).attack());

            if (!defenders.get(target).isAlive()) defenders.remove(target);
            if (!attackers.get(i).isAlive()) attackers.remove(i);
            if (attackers.isEmpty() || defenders.isEmpty()) break;
        }
    }

    private void printResults() {
        if (!firstTeam.isEmpty() && secondTeam.isEmpty()) {
            System.out.println("First team wins!");
        } else if (!secondTeam.isEmpty() && firstTeam.isEmpty()) {
            System.out.println("Second team wins!");
        } else {
            System.out.println("Draw!");
        }
    }
}
