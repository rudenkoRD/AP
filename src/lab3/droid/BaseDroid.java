package lab3.droid;

import java.io.Serializable;
import java.util.Scanner;

abstract class BaseDroid implements Droid, Serializable, Cloneable {
    String name;

    protected BaseDroid(String name) {
        this.name = name;
    }

    protected BaseDroid() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter droid name: ");
        this.name = input.nextLine();
    }

    @Override
    public String getName() {
        return name;
    }

    public Droid copy() throws CloneNotSupportedException {
        return (Droid) super.clone();
    }
}
