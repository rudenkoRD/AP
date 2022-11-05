package controllers;

import model.Flight;
import model.Pilot;
import model.PilotType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import persistence.pilot.PilotsRepository;
import persistence.schedule.ScheduleRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PilotsControllerTest {
    static private final LocalDate birthday = LocalDate.of(2022, 12, 12);
    static private final LocalDate birthdayOlder = LocalDate.of(2020, 12, 12);

    Pilot pilot = new Pilot(1, "Ruslan", birthday, 10, 10.0, PilotType.SECOND_PILOT);
    Pilot pilot1 = new Pilot(2, "Ruslan", birthdayOlder, 100, 10.0, PilotType.SECOND_PILOT);
    String userInput = "1\nRuslan\n12/12/2022\n10\n10.0\nSECOND_PILOT\n";
    String invalidUserInput = "string\n";

    private ByteArrayOutputStream testOut;

    @Mock
    PilotsRepository pilotsRepository;

    @Mock
    ScheduleRepository scheduleRepository;

    @InjectMocks
    PilotsController controller;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @Test
    public void addsPilot(){
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        controller.addPilot();

        verify(pilotsRepository, times(1)).addPilot(pilot);
    }

    @Test
    public void catchesExceptionWhenAddsPilot(){
        ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidUserInput.getBytes());
        System.setIn(inputStream);

        controller.addPilot();

        assertEquals("Failed to add pilot", testOut.toString().split("\n")[2]);
    }

    @Test
    public void sortsPilotsByAge(){
        when(pilotsRepository.readPilots()).thenReturn(Arrays.asList(pilot1, pilot));

        controller.sortPilotsByAge();

        assertEquals(pilot.toString()+"\n"+pilot1.toString()+"\n", testOut.toString());
    }

    @Test
    public void sortsPilotsBySalary(){
        when(pilotsRepository.readPilots()).thenReturn(Arrays.asList(pilot1, pilot));

        controller.sortPilotsBySalary();

        assertEquals(pilot.toString()+"\n"+pilot1.toString()+"\n", testOut.toString());
    }

    @Test
    public void showsPilotsStatuses(){
        when(pilotsRepository.readPilots()).thenReturn(Arrays.asList(pilot1, pilot));

        LocalDateTime now = LocalDateTime.of(2022, 12, 12, 1, 3);
        Clock clock = Clock.fixed(now.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));

        LocalDateTime startOfNowFlight = LocalDateTime.of(2022, 12, 12, 1, 1);
        LocalDateTime endOfNowFlight = LocalDateTime.of(2022, 12, 12, 1, 4);
        Flight flight1 = new Flight(1, 2, 1, startOfNowFlight, endOfNowFlight, 1, new int[]{1});

        LocalDateTime startOfEndedFlight = LocalDateTime.of(2022, 12, 12, 0, 1);
        LocalDateTime endOfEndedFlight = LocalDateTime.of(2022, 12, 12, 1, 0);
        Flight flight2 = new Flight(2, 1, 2, startOfEndedFlight, endOfEndedFlight, 2, new int[]{2});

        when(scheduleRepository.loadSchedule()).thenReturn(Arrays.asList(flight1, flight2));

        controller.showPilotStatuses(clock);

        assertEquals("Pilot #2 is not on flight\n" +
                "Pilot #1 is on flight\n", testOut.toString());
    }
}
