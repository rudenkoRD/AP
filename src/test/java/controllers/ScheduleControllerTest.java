package controllers;

import model.Flight;
import model.Plane;
import model.PlaneType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import persistence.plane.PlanesRepository;
import persistence.schedule.ScheduleRepository;
import utils.DateUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleControllerTest {
    Plane plane = new Plane(1, PlaneType.CARGO, 10.0, 10, 100);
    Plane plane1 = new Plane(2, PlaneType.CARGO, 10.0, 100, 1000);
    static private final LocalDateTime startTime = LocalDateTime.of(2022, 12, 12, 1, 1);
    static private final LocalDateTime inFlightTime = LocalDateTime.of(2022, 12, 12, 1, 2);
    static private final LocalDateTime endTime = LocalDateTime.of(2022, 12, 12, 1, 3);
    static private final LocalDateTime notInFlightTime = LocalDateTime.of(2022, 12, 12, 1, 4);
    Flight flight = new Flight(1, 1, 2, startTime, endTime, 1, new int[]{1});
    static private String userInput;
    String invalidUserInput = "string\n";

    private ByteArrayOutputStream testOut;

    @Mock
    PlanesRepository planesRepository;

    @Mock
    ScheduleRepository scheduleRepository;

    @InjectMocks
    ScheduleController controller;

    @Before
    public void setUpOutput() {
        String startTimeString = DateUtils.formatTime(startTime);
        String endTimeString = DateUtils.formatTime(endTime);

        userInput = "1\n1\n2\n" + startTimeString + "\n" + endTimeString + "\n1\n1";

        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @Test
    public void addsFlight() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        controller.addFlightToSchedule();

        verify(scheduleRepository, times(1)).addFlight(flight);
    }

    @Test
    public void catchesExceptionWhenAddsPilot() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidUserInput.getBytes());
        System.setIn(inputStream);

        controller.addFlightToSchedule();

        assertEquals("Failed to add flight", testOut.toString().split("\n")[2]);
    }

    @Test
    public void updatesFlight() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(("1\n"+DateUtils.formatTime(endTime)).getBytes());
        System.setIn(inputStream);

        controller.updateFlight();

        verify(scheduleRepository, times(1)).updateFlight(1, endTime);
    }

    @Test
    public void catchesExceptionWhenUpdatesFlight() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidUserInput.getBytes());
        System.setIn(inputStream);

        controller.updateFlight();

        assertEquals("Invalid data", testOut.toString().split("\n")[1]);
        assertEquals("Failed to update flight", testOut.toString().split("\n")[2]);
    }

    @Test
    public void getsRemainingFlyingTime(){
        when(planesRepository.readPlanes()).thenReturn(Arrays.asList(plane));
        when(scheduleRepository.loadSchedule()).thenReturn(Arrays.asList(flight));
        Clock clock = Clock.fixed(inFlightTime.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));

        controller.getRemainingFlyTime(clock);

        assertEquals("Plane "+plane.getId()+", remaining fly time "+ ChronoUnit.MINUTES.between(inFlightTime, endTime) +" minutes\n", testOut.toString());
    }

    @Test
    public void getsRemainingFlyingTimeWithNoData(){
        when(planesRepository.readPlanes()).thenReturn(Arrays.asList(plane, plane1));
        when(scheduleRepository.loadSchedule()).thenReturn(Arrays.asList(flight));
        Clock clock = Clock.fixed(notInFlightTime.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));

        controller.getRemainingFlyTime(clock);

        assertEquals("No flights!\n", testOut.toString());
    }
}
