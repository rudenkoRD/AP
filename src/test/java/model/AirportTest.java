package model;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AirportTest {
    static private final Airport airport = new Airport(1, "Odessa", 10);
    static private final JSONObject airportJson = new JSONObject();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);
    String userInput = "1\nOdessa\n10";
    String invalidUserInput = "string\n";

    @BeforeClass
    public static void setUp() {
        airportJson.put("id", 1);
        airportJson.put("location", "Odessa");
        airportJson.put("maxCapacity", 10);
    }

    @Test
    public void createsAirportFromUserInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        Airport userAirport = new Airport(inputStream, printStream);

        assertEquals(airport, userAirport);
    }

    @Test
    public void failsToCreateAirportFromUserInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidUserInput.getBytes());

        assertThrows(Exception.class, () -> new Airport(inputStream, printStream));

        String[] outputText = byteArrayOutputStream.toString().split("\n");

        assertEquals("Invalid data!", outputText[outputText.length - 1]);
    }

    @Test
    public void parsesAirportFromJson() {
        Airport parsedAirport = Airport.fromJson(airportJson);

        assertEquals(airport, parsedAirport);
    }

    @Test
    public void encodesAirportToJson() {
        JSONObject encodedJson = airport.toJson();

        assertEquals(airportJson, encodedJson);
    }
}
