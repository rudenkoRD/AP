package model;

import org.json.simple.JSONObject;
import utils.Generated;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public class Plane {
    private final int id;
    private final PlaneType type;
    private final double fuelConsumption;
    private final int capacity;
    private final double maxFlightDistance;

    @Generated
    public int getId() {
        return id;
    }

    @Generated
    public int getCapacity() {
        return capacity;
    }

    @Generated
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

    public Plane(InputStream inputStream, PrintStream printStream) {
        try {
            Scanner in = new Scanner(inputStream);
            printStream.println("Enter id: ");
            id = in.nextInt();
            printStream.println("Enter Type(CARGO/PRIVATE/COMMERCIAL): ");
            type = PlaneType.valueOf(in.next().toUpperCase());
            printStream.println("Enter fuel consumption: ");
            fuelConsumption = in.nextDouble();
            printStream.println("Enter capacity: ");
            capacity = in.nextInt();
            printStream.println("Enter maximum flight distance: ");
            maxFlightDistance = in.nextDouble();
        } catch (Exception e) {
            printStream.println("Invalid data!");
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

    @Generated
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

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return id == plane.id && Double.compare(plane.fuelConsumption, fuelConsumption) == 0 && capacity == plane.capacity && Double.compare(plane.maxFlightDistance, maxFlightDistance) == 0 && type == plane.type;
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(id, type, fuelConsumption, capacity, maxFlightDistance);
    }
}
