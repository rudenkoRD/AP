package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.DateUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.*;

import static org.junit.Assert.*;

public class FlightTest {
    static private final JSONObject flightJson = new JSONObject();
    static private final LocalDateTime startTime = LocalDateTime.of(2022, 12, 12, 1, 1);
    static private final LocalDateTime inFlightTime = LocalDateTime.of(2022, 12, 12, 1, 2);
    static private final LocalDateTime endTime = LocalDateTime.of(2022, 12, 12, 1, 3);
    static private final LocalDateTime beforeFlightTime = LocalDateTime.of(2022, 12, 12, 0, 0);
    static private final LocalDateTime afterFlightTime = LocalDateTime.of(2022, 12, 12, 1, 4);
    Flight flight = new Flight(1, 1, 2, startTime, endTime, 1, new int[]{1});
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);
    static private String userInput;
    String invalidUserInput = "string\n";

    @BeforeClass
    public static void setUp() {
        String startTimeString = DateUtils.formatTime(startTime);
        String endTimeString = DateUtils.formatTime(endTime);

        userInput = "1\n1\n2\n" + startTimeString + "\n" + endTimeString + "\n1\n1";

        flightJson.put("id", 1);
        flightJson.put("startAirportId", 1);
        flightJson.put("arrivalAirportId", 2);
        flightJson.put("startTime", startTimeString);
        flightJson.put("endTime", endTimeString);
        flightJson.put("planeId", 1);
        JSONArray pilotsIdsJson = new JSONArray();
        pilotsIdsJson.add(1);
        flightJson.put("pilotIds", pilotsIdsJson);
    }

    @Test
    public void createsAirportFromUserInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        Flight userFlight = new Flight(inputStream, printStream);

        assertEquals(flight, userFlight);
    }

    @Test
    public void failsToCreateAirportFromUserInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidUserInput.getBytes());

        assertThrows(Exception.class, () -> new Flight(inputStream, printStream));

        String[] outputText = byteArrayOutputStream.toString().split("\n");

        assertEquals("Invalid data", outputText[outputText.length - 1]);
    }

    @Test
    public void parsesAirportFromJson() {
        Flight parsedFlight = Flight.fromJson(flightJson);

        assertEquals(flight, parsedFlight);
    }

    @Test
    public void encodesAirportToJson() {
        JSONObject encodedJson = flight.toJson();

        assertEquals(flightJson, encodedJson);
    }

    @Test
    public void flightIsNow(){
        Clock clock = Clock.fixed(inFlightTime.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
        assertTrue(flight.isNow(clock));
    }

    @Test
    public void flightIsAlreadyHappened(){
        Clock clock = Clock.fixed(afterFlightTime.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
        assertFalse(flight.isNow(clock));
    }

    @Test
    public void flightIsToHappen(){
        Clock clock = Clock.fixed(beforeFlightTime.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
        assertFalse(flight.isNow(clock));
    }
}
