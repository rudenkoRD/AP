package persistence.pilot;

import model.Pilot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.IgnoreInTests;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AppPilotsRepository implements PilotsRepository {
    private static final String FILE = "src/main/resources/pilots.json";

    @IgnoreInTests
    @Override
    public List<Pilot> readPilots() {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader pilotsJsonfile = new FileReader(FILE);
            JSONArray jsonArray = (JSONArray) jsonParser.parse(pilotsJsonfile);

            List<Pilot> airports = new ArrayList<>();

            for (Object o : jsonArray) {
                airports.add(Pilot.fromJson((JSONObject) o));
            }

            return airports;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @IgnoreInTests
    @Override
    public void addPilot(Pilot newPilot) {
        List<Pilot> pilots = readPilots();

        if (pilots.stream().anyMatch(pilot -> pilot.getId() == newPilot.getId())) {
            System.out.println("The pilot is already existing!");
            return;
        }

        pilots.add(newPilot);
        JSONArray pilotsJson = new JSONArray();
        pilots.forEach(pilot -> pilotsJson.add(pilot.toJson()));

        try (FileWriter file = new FileWriter(FILE)) {
            file.write(pilotsJson.toJSONString());
            file.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
