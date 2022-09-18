package lab3.droid;

public interface Droid {
    Attack attack();

    void receiveAttack(Attack attack);

    boolean isAlive();

    String getName();

    Droid copy() throws CloneNotSupportedException;
}
