package lab3.droid;

public class Defender extends HealthDroid {
    public Defender(String name, int defence, int health) {
        super(name, defence, health);
    }

    public Defender() {
        System.out.println("Droid created!\n");
    }

    @Override
    public Attack attack() {
        return new Attack();
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nHealth: " + health + "\nDefence: " + defense;
    }
}
