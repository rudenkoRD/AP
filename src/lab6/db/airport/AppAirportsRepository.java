package lab6.db.airport;

import lab6.model.Airport;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AppAirportsRepository implements AirportsRepository {
    static private final String FILE = "src/lab6/db/data/airports.json";

    @Override
    public List<Airport> readAirports() {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader airplanesJsonfile = new FileReader(FILE);
            JSONArray jsonArray = (JSONArray) jsonParser.parse(airplanesJsonfile);

            List<Airport> airports = new ArrayList<>();

            for (Object o : jsonArray) {
                airports.add(Airport.fromJson((JSONObject) o));
            }

            return airports;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addAirport(Airport newAirport) {
        List<Airport> airports = readAirports();

        if (airports.stream().anyMatch(airport -> airport.getId() == newAirport.getId())) {
            System.out.println("The airport is already existing!");
            return;
        }

        airports.add(newAirport);
        JSONArray airportsJson = new JSONArray();
        airports.forEach(airport -> airportsJson.add(airport.toJson()));

        try (FileWriter file = new FileWriter(FILE)) {

            file.write(airportsJson.toJSONString());
            file.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
