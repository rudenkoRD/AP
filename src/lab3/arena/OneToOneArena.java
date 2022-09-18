package lab3.arena;

import lab3.droid.Droid;
import lab3.output.Output;
import lab3.writer.ArenaIO;

import java.io.Serializable;

public class OneToOneArena implements Arena, Serializable {
    Droid firstDroid;
    Droid secondDroid;

    public OneToOneArena(Droid firstDroid, Droid secondDroid) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;
    }

    @Override
    public void simulateBattle(boolean record) {
        if (record) ArenaIO.write(this, "record.obj");

        int roundCount = 0;
        while (firstDroid.isAlive() && secondDroid.isAlive()) {
            secondDroid.receiveAttack(firstDroid.attack());

            if (!secondDroid.isAlive()) break;

            System.out.print(Output.ANSI_BLACK_BACKGROUND);
            firstDroid.receiveAttack(secondDroid.attack());
            System.out.print(Output.ANSI_RESET);

            roundCount++;
            if (roundCount > 100) {
                break;
            }
        }

        printResults();
    }

    private void printResults() {
        if (firstDroid.isAlive() && secondDroid.isAlive()) {
            System.out.println("Draw!");
        } else if (firstDroid.isAlive()) {
            System.out.println(firstDroid.getName() + " droid wins!");
        } else if (secondDroid.isAlive()) {
            System.out.println(secondDroid.getName() + " droid wins!");
        }
    }
}
