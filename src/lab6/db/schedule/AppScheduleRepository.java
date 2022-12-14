package lab6.db.schedule;

import lab6.model.Flight;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppScheduleRepository implements ScheduleRepository {
    private static final String FILE = "src/lab6/db/data/schedule.json";

    @Override
    public List<Flight> loadSchedule() {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader scheduleJsonfile = new FileReader(FILE);
            JSONArray jsonArray = (JSONArray) jsonParser.parse(scheduleJsonfile);

            List<Flight> flights = new ArrayList<>();

            for (Object o : jsonArray) {
                flights.add(Flight.fromJson((JSONObject) o));
            }

            return flights;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addFlight(Flight newFlight) {
        List<Flight> flights = loadSchedule();

        if (flights.stream().anyMatch(flight -> flight.getId() == newFlight.getId())) {
            System.out.println("The flight is already existing!");
            return;
        }

        flights.add(newFlight);
        writeFlights(flights);
    }

    @Override
    public void updateFlight(int id, LocalDateTime endTime) {
        List<Flight> flights = loadSchedule();

        int index = -1;
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getId() == id) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("No light with given id: " + id);
            return;
        }

        flights.get(index).setEndTime(endTime);
        writeFlights(flights);
    }

    private void writeFlights(List<Flight> flights) {
        JSONArray flightJson = new JSONArray();
        flights.forEach(flight -> flightJson.add(flight.toJson()));

        try (FileWriter file = new FileWriter(FILE)) {
            file.write(flightJson.toJSONString());
            file.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
