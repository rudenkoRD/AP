package controllers;

import model.Airport;
import model.Flight;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import persistence.airport.AirportsRepository;
import persistence.schedule.ScheduleRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AirportsControllerTest {
    Airport airport = new Airport(1, "Odessa", 10);
    String userInput = "1\nOdessa\n10";
    String invalidUserInput = "string\n";

    private ByteArrayOutputStream testOut;

    @Mock
    AirportsRepository airportsRepository;

    @Mock
    ScheduleRepository scheduleRepository;

    @InjectMocks
    AirportsController controller;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @Test
    public void addsAirport(){
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        controller.addAirport();

        verify(airportsRepository, times(1)).addAirport(airport);
    }

    @Test
    public void catchesExceptionWhenAddsAirport(){
        ByteArrayInputStream inputStream = new ByteArrayInputStream(invalidUserInput.getBytes());
        System.setIn(inputStream);

        controller.addAirport();

        assertEquals("Failed to add airport", testOut.toString().split("\n")[2]);
    }

    @Test
    public void getNumberOfPlanesInAirportsWhenNoData(){
        when(scheduleRepository.loadSchedule()).thenReturn(new ArrayList<>());
        when(airportsRepository.readAirports()).thenReturn(new ArrayList<>());

        controller.getNumberOfPlanesInAirports(Clock.systemDefaultZone());

        assertEquals("No data\n", testOut.toString());
    }

    @Test
    public void getNumberOfPlanesInAirports(){
        LocalDateTime now = LocalDateTime.of(2022, 12, 12, 1, 3);
        Clock clock = Clock.fixed(now.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));

        LocalDateTime start1 = LocalDateTime.of(2022, 12, 12, 1, 1);
        LocalDateTime end1 = LocalDateTime.of(2022, 12, 12, 1, 2);
        Flight flight1 = new Flight(1, 2, 1, start1, end1, 1, new int[]{1});
        Flight flight2 = new Flight(2, 1, 2, start1, end1, 2, new int[]{1});
        Flight flight3 = new Flight(2, 1, 2, start1, end1, 3, new int[]{1});

        LocalDateTime startNowFlying = LocalDateTime.of(2022, 12, 12, 1, 1);
        LocalDateTime endNowFlying = LocalDateTime.of(2022, 12, 12, 1, 4);
        Flight flight4 = new Flight(2, 1, 2, startNowFlying, endNowFlying, 4, new int[]{1});

        LocalDateTime futureStart = LocalDateTime.of(2022, 12, 12, 1, 10);
        LocalDateTime futureEnd = LocalDateTime.of(2022, 12, 12, 2, 0);
        Flight flight5 = new Flight(2, 1, 2, futureStart, futureEnd, 2, new int[]{1});

        LocalDateTime pastStart = LocalDateTime.of(2022, 12, 12, 0, 1);
        LocalDateTime pastEnd = LocalDateTime.of(2022, 12, 12, 0, 2);
        Flight flight6 = new Flight(2, 1, 2, pastStart, pastEnd, 2, new int[]{1});

        Airport airport1 = new Airport(1, "Location", 10);
        Airport airport2 = new Airport(2, "Location", 10);

        when(scheduleRepository.loadSchedule()).thenReturn(Arrays.asList(flight1, flight2, flight3, flight4, flight5,flight6));
        when(airportsRepository.readAirports()).thenReturn(Arrays.asList(airport1, airport2));

        controller.getNumberOfPlanesInAirports(clock);

        assertEquals("Airport #1 contains 1 plane(s)\n" +
                "Airport #2 contains 2 plane(s)\n", testOut.toString());
    }
}
