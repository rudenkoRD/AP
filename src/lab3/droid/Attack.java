package lab3.droid;

public class Attack {
    Effect effect;
    Integer damage;

    public Attack() {
    }

    public Attack(int damage) {
        this.damage = damage;
    }

    public Attack(Effect effect) {
        this.effect = effect;
    }

    public Attack(Effect effect, int damage) {
        this.effect = effect;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "(damage: " + damage + ")";
    }
}
