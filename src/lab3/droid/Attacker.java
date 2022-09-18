package lab3.droid;

import java.util.Scanner;

public class Attacker extends HealthDroid {
    private final int damage;

    public Attacker(String name, int health, int defense, int damage) {
        super(name, defense, health);
        this.damage = damage;
    }

    public Attacker() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter droid damage: ");
        this.damage = input.nextInt();
        System.out.println("Droid created!\n");
    }

    @Override
    public Attack attack() {
        return new Attack(damage);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nHealth: " + health + "\nDefence: " + defense + "\ndamage: " + damage;
    }
}
