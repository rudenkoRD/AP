package lab6.model;

import org.json.simple.JSONObject;

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

    public int getId() {
        return id;
    }

    public Airport() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter id: ");
            id = in.nextInt();
            System.out.println("Enter location: ");
            location = in.next();
            System.out.println("Enter max capacity: ");
            maxCapacity = in.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid data!");
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

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}

