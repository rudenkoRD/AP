package lab3.droid;

import lab3.output.Output;

import java.util.Scanner;

public abstract class HealthDroid extends BaseDroid {
    protected int defense;
    protected int health;
    protected final int initialHealth;

    protected HealthDroid(String name, int defense, int health) {
        super(name);
        this.defense = defense;
        this.health = health;
        this.initialHealth = health;
    }

    protected HealthDroid() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter droid health: ");
        health = input.nextInt();
        initialHealth = health;
        System.out.println("Enter droid defence: ");
        defense = input.nextInt();
    }

    @Override
    public void receiveAttack(Attack attack) {
        if (attack.damage != null) {
            health -= Math.max(0, attack.damage - defense);
            health = Math.max(health, 0);

            double healthPercent = (double) health / initialHealth * 100.0;
            System.out.println(name + "(defense: " + defense + ") health: " + Output.health(health, healthPercent) + ", after attack" + attack);
        }

        if (attack.effect != null) {
            health -= attack.effect.healthReduction;
            defense -= attack.effect.defenseReduction;
            health = Math.max(health, 0);
            defense = Math.max(defense, 0);
            double healthPercent = (double) health / initialHealth * 100.0;
            System.out.println(name + "(defense: " + defense + ") health: " + Output.health(health, healthPercent) + ", after effect\n" + attack.effect);
        }
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
