package lab1;

/**
 * Base sequence class
 *
 * @author Ruslan
 * @version 1.0.0
 */
public interface Sequence {
    void setAmountOfNumbers(int amountOfNumbers);

    int getAmountOfNumbers();

    long getSumOfFirstNNumbers();
}
