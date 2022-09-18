package lab3.droid;

import java.util.Scanner;

public abstract class EnergyDroid extends BaseDroid {
    protected int energy;
    protected final int initialEnergy;

    protected EnergyDroid(String name, int energy) {
        super(name);
        this.energy = energy;
        this.initialEnergy = energy;
    }

    protected EnergyDroid() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter droid energy: ");
        this.energy = input.nextInt();
        this.initialEnergy = energy;
    }

    @Override
    public boolean isAlive() {
        return energy > 0;
    }
}
