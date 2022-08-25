package lab1;

import java.util.Scanner;


/**
 * This class solves task for Lab1, 22 variant
 *
 * @author Ruslan
 * @version 1.0.0
 */
public class Lab1 {

    public static void main(String[] args) {
        new Lab1().run(args);
    }


    /**
     * Performs necessary calculations and outputs result for Lab1
     */
    void run(String[] args) {
        Sequence lukeSequence = new LukeSequence();

        lukeSequence.setAmountOfNumbers(readLukeNumber(args));

        long sumOfLukeNumbers = lukeSequence.getSumOfFirstNNumbers();

        System.out.printf(
                "Sum of first %d Luke sequence numbers: %d\n",
                lukeSequence.getAmountOfNumbers(),
                sumOfLukeNumbers);
    }

    /**
     * Reads number from arguments, if not able then from command line
     */
    int readLukeNumber(String[] args) {
        try {
            return Integer.parseInt(args[0]);
        } catch (Exception e) {
            return readLukeNumberFromTerminal();
        }
    }

    /**
     * Reads number from terminal
     */
    int readLukeNumberFromTerminal() {
        System.out.println("Enter amount of Luke numbers to calculate sum(up to 90): ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
