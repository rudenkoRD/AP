package lab6.model;

import lab6.utils.DateUtils;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pilot {
    private final int id;
    private final String name;
    private final LocalDate birthday;
    private final int salary;
    private final double flyingHours;
    private final PilotType type;

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

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

    public Pilot() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter id: ");
            id = in.nextInt();
            System.out.println("Enter name: ");
            in.nextLine();
            name = in.nextLine();
            System.out.println("Enter birthday(dd/mm/yyyy format): ");
            birthday = DateUtils.parseDate(in.nextLine());
            System.out.println("Enter salary: ");
            salary = in.nextInt();
            System.out.println("Enter flying hours: ");
            flyingHours = in.nextDouble();
            System.out.println("Enter pilot type(CAPTAIN/SECOND_PILOT): ");
            String pilotType = in.next();
            type = PilotType.valueOf(pilotType);
        } catch (Exception e) {
            System.out.println("Invalid data!");
            throw e;
        }
    }

    public static Pilot fromJson(JSONObject json) throws Exception {
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
}
