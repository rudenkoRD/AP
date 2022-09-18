package lab3.droid;

import lab3.output.Output;

public class Hacker extends EnergyDroid {
    private final Effect effect;

    public Hacker(String name, int energy, Effect effect) {
        super(name, energy);
        this.effect = effect;
    }

    public Hacker() {
        this.effect = new Effect();
    }

    @Override
    public Attack attack() {
        energy -= effect.energyConsumption;
        Attack attack = new Attack();

        if (energy >= 0) {
            attack = new Attack(effect);
        }

        energy = Math.max(energy, 0);
        if (!isAlive()) {
            System.out.println(name + " run out of energy!");
        }

        return attack;
    }

    @Override
    public void receiveAttack(Attack attack) {
        if (attack.effect != null) {
            energy -= attack.effect.energyReduction;

            System.out.println(name + "health: " + Output.health(energy, initialEnergy) + ", after effect" + effect + Output.ANSI_RESET);
        } else if (attack.damage != null) {
            System.out.println("Attack missed on " + name);
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nEnergy: \n" + energy + "\nEffect: \n" + effect;
    }
}
