package controllers;

import model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import persistence.plane.PlanesRepository;
import persistence.schedule.ScheduleRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlanesControllerTest {
    Plane plane = new Plane(1, PlaneType.CARGO, 10.0, 10, 100);
    Plane plane1 = new Plane(2, PlaneType.CARGO, 10.0, 100, 1000);
    String userInput = "1\nCARGO\n10.0\n10\n100\n";
    String invalidUserInput = "string\n";

    private ByteArrayOutputStream testOut;

    @Mock
    PlanesRepository planesRepository;

    @Mock
    ScheduleRepository scheduleRepository;

    @InjectMocks
    PlanesController controller;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @Test
    public void addPlane() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        controller.addPlane();

        verify(planesRepository, times(1)).addPlane(plane);
    }

    @Test
    public void catchesExceptionWhenAddsPilot() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidUserInput.getBytes());
        System.setIn(inputStream);

        controller.addPlane();

        assertEquals("Failed to add plane", testOut.toString().split("\n")[2]);
    }

    @Test
    public void sortsPlanesByCapacity() {
        when(planesRepository.readPlanes()).thenReturn(Arrays.asList(plane1, plane));

        controller.sortPlanesByCapacity();

        assertEquals(plane.toString() + "\n" + plane1.toString() + "\n", testOut.toString());
    }

    @Test
    public void sortsPilotsByAvailableDistance() {
        when(planesRepository.readPlanes()).thenReturn(Arrays.asList(plane1, plane));

        controller.sortPlanesByAvailableDistance();

        assertEquals(plane.toString() + "\n" + plane1.toString() + "\n", testOut.toString());
    }

    @Test
    public void getsPlanesStatuses(){
        when(planesRepository.readPlanes()).thenReturn(Arrays.asList(plane1, plane));

        LocalDateTime now = LocalDateTime.of(2022, 12, 12, 1, 3);
        Clock clock = Clock.fixed(now.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));

        LocalDateTime startOfNowFlight = LocalDateTime.of(2022, 12, 12, 1, 1);
        LocalDateTime endOfNowFlight = LocalDateTime.of(2022, 12, 12, 1, 4);
        Flight flight1 = new Flight(1, 2, 1, startOfNowFlight, endOfNowFlight, 1, new int[]{1});

        LocalDateTime startOfEndedFlight = LocalDateTime.of(2022, 12, 12, 0, 1);
        LocalDateTime endOfEndedFlight = LocalDateTime.of(2022, 12, 12, 1, 0);
        Flight flight2 = new Flight(2, 1, 2, startOfEndedFlight, endOfEndedFlight, 2, new int[]{2});

        when(scheduleRepository.loadSchedule()).thenReturn(Arrays.asList(flight1, flight2));

        controller.getPlanesStatuses(clock);

        assertEquals("Plan #2 is not flying\n" +
                "Plan #1 is flying\n", testOut.toString());
    }
}
