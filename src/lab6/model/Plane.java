package lab6.model;

import org.json.simple.JSONObject;

import java.util.Scanner;

public class Plane {
    private final int id;
    private final PlaneType type;
    private final double fuelConsumption;
    private final int capacity;
    private final double maxFlightDistance;

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public Plane(int id, PlaneType type, double fuelConsumption, int capacity, double maxFlightDistance) {
        this.id = id;
        this.type = type;
        this.fuelConsumption = fuelConsumption;
        this.capacity = capacity;
        this.maxFlightDistance = maxFlightDistance;
    }

    public Plane() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter id: ");
            id = in.nextInt();
            System.out.println("Enter Type(CARGO/PRIVATE/COMMERCIAL): ");
            type = PlaneType.valueOf(in.next().toUpperCase());
            System.out.println("Enter fuel consumption: ");
            fuelConsumption = in.nextDouble();
            System.out.println("Enter capacity: ");
            capacity = in.nextInt();
            System.out.println("Enter maximum flight distance: ");
            maxFlightDistance = in.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid data!");
            throw e;
        }
    }

    public static Plane fromJson(JSONObject json) {
        String id = json.get("id").toString();
        String type = json.get("type").toString();
        String fuelConsumption = json.get("fuelConsumption").toString();
        String capacity = json.get("capacity").toString();
        String maxFlightDistance = json.get("maxFlightDistance").toString();

        return new Plane(
                Integer.parseInt(id),
                PlaneType.valueOf(type),
                Double.parseDouble(fuelConsumption),
                Integer.parseInt(capacity),
                Double.parseDouble(maxFlightDistance)
        );
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("type", type.name());
        json.put("fuelConsumption", fuelConsumption);
        json.put("capacity", capacity);
        json.put("maxFlightDistance", maxFlightDistance);
        return json;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", type=" + type +
                ", fuelConsumption=" + fuelConsumption +
                ", capacity=" + capacity +
                ", maxFlightDistance=" + maxFlightDistance +
                '}';
    }
}
