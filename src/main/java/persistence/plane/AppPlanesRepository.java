package persistence.plane;

import model.Plane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.IgnoreInTests;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AppPlanesRepository implements PlanesRepository {
    private static final String FILE = "src/main/resources/planes.json";

    @IgnoreInTests
    @Override
    public List<Plane> readPlanes() {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader planesJsonfile = new FileReader(FILE);
            JSONArray jsonArray = (JSONArray) jsonParser.parse(planesJsonfile);

            List<Plane> planes = new ArrayList<>();

            for (Object o : jsonArray) {
                planes.add(Plane.fromJson((JSONObject) o));
            }

            return planes;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @IgnoreInTests
    @Override
    public void addPlane(Plane newPlane) {
        List<Plane> planes = readPlanes();

        if (planes.stream().anyMatch(plane -> plane.getId() == newPlane.getId())) {
            System.out.println("The plane is already existing!");
            return;
        }

        planes.add(newPlane);
        JSONArray planesJson = new JSONArray();
        planes.forEach(plane -> planesJson.add(plane.toJson()));

        try (FileWriter file = new FileWriter(FILE)) {
            file.write(planesJson.toJSONString());
            file.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
