package model;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.DateUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PilotTest {
    static private final LocalDate birthday = LocalDate.of(2022, 12, 12);
    static private final Pilot pilot = new Pilot(1, "Ruslan",birthday, 100, 10.0, PilotType.CAPTAIN);
    static private final JSONObject pilotJson = new JSONObject();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);
    static private String userInput;
    String invalidUserInput = "string\n";

    @BeforeClass
    public static void setUp() {
        String birthdayString = DateUtils.formatDate(birthday);
        userInput = "1\nRuslan\n"+birthdayString+"\n100\n10.0\nCAPTAIN";

        pilotJson.put("id", 1);
        pilotJson.put("name", "Ruslan");
        pilotJson.put("birthday", birthdayString);
        pilotJson.put("salary", 100);
        pilotJson.put("flyingHours", 10.0);
        pilotJson.put("type", "CAPTAIN");
    }

    @Test
    public void createsAirportFromUserInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        Pilot userPilot = new Pilot(inputStream, printStream);

        assertEquals(pilot, userPilot);
    }

    @Test
    public void failsToCreateAirportFromUserInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidUserInput.getBytes());

        assertThrows(Exception.class, () -> new Pilot(inputStream, printStream));

        String[] outputText = byteArrayOutputStream.toString().split("\n");

        assertEquals("Invalid data!", outputText[outputText.length - 1]);
    }

    @Test
    public void parsesAirportFromJson() {
        Pilot parsedPilot = Pilot.fromJson(pilotJson);

        assertEquals(pilot, parsedPilot);
    }

    @Test
    public void encodesAirportToJson() {
        JSONObject encodedJson = pilot.toJson();

        assertEquals(pilotJson, encodedJson);
    }
}
