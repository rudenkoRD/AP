package model;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PlaneTest {
    static private final Plane plane = new Plane(1, PlaneType.CARGO,10.0,10, 100);
    static private final JSONObject planeJson = new JSONObject();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);
    String userInput = "1\nCARGO\n10.0\n10\n100";
    String invalidUserInput = "string\n";

    @BeforeClass
    public static void setUp() {
        planeJson.put("id", 1);
        planeJson.put("type", "CARGO");
        planeJson.put("fuelConsumption", 10.0);
        planeJson.put("capacity", 10);
        planeJson.put("maxFlightDistance", 100.0);
    }

    @Test
    public void createsAirportFromUserInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        Plane userPlane = new Plane(inputStream, printStream);

        assertEquals(plane, userPlane);
    }

    @Test
    public void failsToCreateAirportFromUserInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidUserInput.getBytes());

        assertThrows(Exception.class, () -> new Plane(inputStream, printStream));

        String[] outputText = byteArrayOutputStream.toString().split("\n");

        assertEquals("Invalid data!", outputText[outputText.length - 1]);
    }

    @Test
    public void parsesAirportFromJson() {
        Plane parsedPlane = Plane.fromJson(planeJson);

        assertEquals(plane, parsedPlane);
    }

    @Test
    public void encodesAirportToJson() {
        JSONObject encodedJson = plane.toJson();

        assertEquals(planeJson, encodedJson);
    }
}
