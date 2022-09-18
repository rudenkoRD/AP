package lab3.droid;

import java.io.Serializable;
import java.util.Scanner;

public class Effect implements Serializable {
    protected final int energyConsumption;
    protected final int healthReduction;
    protected final int defenseReduction;
    protected final int energyReduction;

    public Effect(int energyConsumption, int healthReduction, int defenseReduction, int energyReduction) {
        this.energyConsumption = energyConsumption;
        this.healthReduction = healthReduction;
        this.defenseReduction = defenseReduction;
        this.energyReduction = energyReduction;
    }

    public Effect() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter energy consumption: ");
        this.energyConsumption = input.nextInt();
        System.out.println("Enter health reduction: ");
        this.healthReduction = input.nextInt();
        System.out.println("Enter defense reduction: ");
        this.defenseReduction = input.nextInt();
        System.out.println("Enter energy reduction: ");
        this.energyReduction = input.nextInt();
    }

    @Override
    public String toString() {
        return "(Energy consumption: " + energyConsumption +
                "\nhealth reduction: " + healthReduction +
                "\ndefense reduction: " + defenseReduction +
                "\nenergy reduction: " + defenseReduction +
                ")";
    }
}
