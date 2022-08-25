package lab1;

/**
 * Implements Sequence, allows to set amount of numbers and calculate sum of them
 *
 * @author Ruslan
 * @version 1.0.0
 */
public class LukeSequence implements Sequence {
    private int amountOfNumbers;

    /**
     * amountOfNumbers setter
     * Note: when number is bigger than MAX_LUKE_NUMBER
     * it sets amountOfNumbers to MAX_LUKE_NUMBER
     */
    @Override
    public void setAmountOfNumbers(int amountOfNumbers) {
        if (amountOfNumbers > Constants.MAX_LUKE_NUMBER) {
            System.out.printf("Maximum amount of numbers is %d\n", Constants.MAX_LUKE_NUMBER);
            amountOfNumbers = Constants.MAX_LUKE_NUMBER;
        }
        this.amountOfNumbers = amountOfNumbers;
    }

    /**
     * amountOfNumbers getter
     */
    @Override
    public int getAmountOfNumbers() {
        return amountOfNumbers;
    }

    /**
     * Returns sum of first N numbers of Luke sequence
     */
    @Override
    public long getSumOfFirstNNumbers() {
        return getNLukeNumber(amountOfNumbers + 1) - 1;
    }

    /**
     * if n is less than 68 uses fast calculation via Golden ratio
     * otherwise uses standard iterative approach
     *
     * @param n from 1 to 89
     */
    private long getNLukeNumber(int n) {
        if (n <= 0) return 0;
        if (n <= 67) return getNLukeNumberFast(n);
        long prev = 2;
        long cur = 1;
        long temp;

        for (int i = 1; i < n; i++) {
            temp = cur;
            cur += prev;
            prev = temp;
        }

        return cur;
    }

    /**
     * Calculates Luke sequence number using golden radio
     *
     * @param n when over 67 gives not precise results
     */
    private long getNLukeNumberFast(int n) {
        return Math.round(Math.pow(Constants.GOLDEN_RATIO, n));
    }
}
