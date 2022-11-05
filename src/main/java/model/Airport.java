package model;

import org.json.simple.JSONObject;
import utils.IgnoreInTests;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public class Airport {
    private final int id;
    private final String location;
    private final int maxCapacity;

    public Airport(int id, String location, int maxCapacity) {
        this.id = id;
        this.location = location;
        this.maxCapacity = maxCapacity;
    }

    @IgnoreInTests
    public int getId() {
        return id;
    }

    public Airport(InputStream inputStream, PrintStream printStream) {
        try {
            Scanner in = new Scanner(inputStream);
            printStream.println("Enter id: ");
            id = in.nextInt();
            printStream.println("Enter location: ");
            location = in.next();
            printStream.println("Enter max capacity: ");
            maxCapacity = in.nextInt();
        } catch (Exception e) {
            printStream.println("Invalid data!");
            throw e;
        }
    }

    public static Airport fromJson(JSONObject json) {
        String id = json.get("id").toString();
        String maxCapacity = json.get("maxCapacity").toString();

        return new Airport(
                Integer.parseInt(id),
                (String) json.get("location"),
                Integer.parseInt(maxCapacity)
        );
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("location", location);
        json.put("maxCapacity", maxCapacity);
        return json;
    }

    @IgnoreInTests
    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", maxCapacity=" + maxCapacity +
                '}';
    }

    @IgnoreInTests
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return id == airport.id && maxCapacity == airport.maxCapacity && Objects.equals(location, airport.location);
    }

    @IgnoreInTests
    @Override
    public int hashCode() {
        return Objects.hash(id, location, maxCapacity);
    }
}

