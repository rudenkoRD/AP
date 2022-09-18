package lab3.droid;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DroidsManager {
    private final List<Droid> droids;

    public DroidsManager() {
        droids = new ArrayList<>();
        droids.add(new Attacker("first", 100, 10 ,10));
        droids.add(new Attacker("second", 100, 0 ,20));
        droids.add(new Attacker("third", 100, 0 ,20));
    }

    public List<Droid> getAllDroids() {
        return droids;
    }

    public Droid[] getAttackDroids() {
        return filterDroids((droid) -> droid instanceof Attacker);
    }

    private Droid[] filterDroids(Predicate<Droid> compare) {
        return droids.stream().filter(compare).toArray(Droid[]::new);
    }

    public void addDroid(Droid droid) {
        droids.add(droid);
    }

    private void showDroids(Predicate<Droid> compare) {
        Droid[] showDroids = filterDroids(compare);

        if (showDroids.length == 0) {
            System.out.println("No droids!");
        }

        for (int i = 1; i <= showDroids.length; i++) {
            System.out.println("#" + i + "\n" + showDroids[i - 1] + "\n");
        }
    }

    public void showAllDroids() {
        showDroids((droid) -> true);
    }

    public void showAttackerDroids() {
        showDroids((droid) -> droid instanceof Attacker);
    }
}
