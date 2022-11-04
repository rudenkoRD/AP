package model;

import org.json.simple.JSONObject;
import utils.DateUtils;
import utils.Generated;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Scanner;

public class Pilot {
    private final int id;
    private final String name;
    private final LocalDate birthday;
    private final int salary;
    private final double flyingHours;
    private final PilotType type;

    @Generated
    public int getSalary() {
        return salary;
    }

    @Generated
    public int getId() {
        return id;
    }

    @Generated
    public long age() {
        LocalDate now = LocalDate.now();

        return ChronoUnit.DAYS.between(birthday, now);
    }

    public Pilot(int id, String name, LocalDate birthday, int salary, double flyingHours, PilotType type) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.salary = salary;
        this.flyingHours = flyingHours;
        this.type = type;
    }

    public Pilot(InputStream inputStream, PrintStream printStream) {
        try {
            Scanner in = new Scanner(inputStream);
            printStream.println("Enter id: ");
            id = in.nextInt();
            printStream.println("Enter name: ");
            in.nextLine();
            name = in.nextLine();
            printStream.println("Enter birthday(dd/mm/yyyy format): ");
            birthday = DateUtils.parseDate(in.nextLine());
            printStream.println("Enter salary: ");
            salary = in.nextInt();
            printStream.println("Enter flying hours: ");
            flyingHours = in.nextDouble();
            printStream.println("Enter pilot type(CAPTAIN/SECOND_PILOT): ");
            String pilotType = in.next();
            type = PilotType.valueOf(pilotType);
        } catch (Exception e) {
            printStream.println("Invalid data!");
            throw e;
        }
    }

    public static Pilot fromJson(JSONObject json) {
        String id = json.get("id").toString();
        String name = json.get("name").toString();
        String date = json.get("birthday").toString();
        String salary = json.get("salary").toString();
        String flyingHours = json.get("flyingHours").toString();
        String type = json.get("type").toString();

        return new Pilot(
                Integer.parseInt(id),
                name,
                DateUtils.parseDate(date),
                Integer.parseInt(salary),
                Double.parseDouble(flyingHours),
                PilotType.valueOf(type)
        );
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("birthday", DateUtils.formatDate(birthday));
        json.put("salary", salary);
        json.put("flyingHours", flyingHours);
        json.put("type", type.name());
        return json;
    }

    @Generated
    @Override
    public String toString() {
        return "Pilot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", flyingHours=" + flyingHours +
                ", type=" + type +
                '}';
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pilot pilot = (Pilot) o;
        return id == pilot.id && salary == pilot.salary && Double.compare(pilot.flyingHours, flyingHours) == 0 && Objects.equals(name, pilot.name) && Objects.equals(birthday, pilot.birthday) && type == pilot.type;
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthday, salary, flyingHours, type);
    }
}
