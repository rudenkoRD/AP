package controllers;

import db.airport.AirportsRepository;
import db.schedule.ScheduleRepository;
import model.Airport;
import model.Flight;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class AirportsController {
    private final AirportsRepository repository;
    private final ScheduleRepository scheduleRepository;

    public AirportsController(AirportsRepository repository, ScheduleRepository scheduleRepository) {
        this.repository = repository;
        this.scheduleRepository = scheduleRepository;
    }

    public void getNumberOfPlanesInAirports() {
        List<Flight> flights = scheduleRepository.loadSchedule();
        Map<Integer, Integer> res = new HashMap<>();

        Map<Integer, List<Flight>> groupedFlightsByPlane = flights.stream().collect(groupingBy(Flight::getPlaneId));

        groupedFlightsByPlane.forEach((planeId, planeFlights) -> {
            planeFlights.sort(Comparator.comparing(Flight::getEndTime).reversed());
            LocalDateTime now = LocalDateTime.now();

            for (Flight flight : planeFlights) {
                LocalDateTime start = flight.getStartTime();
                LocalDateTime end = flight.getEndTime();
                if (start.isAfter(now) && end.isBefore(now)) break;

                Integer id = flight.getArrivalAirportId();

                if (end.isBefore(now)) {
                    if (res.containsKey(id)) {
                        res.put(id, res.get(id) + 1);
                    } else res.put(id, 1);
                }
            }
        });

        if (res.entrySet().isEmpty()) {
            System.out.println("No data");
        }

        res.forEach((airportId, numberOfPlanes) ->
                System.out.printf("Airport #%d contains %d plane(s)\n", airportId, numberOfPlanes));
    }

    public void addAirport() {
        try {
            repository.addAirport(new Airport());
        } catch (Exception e) {
            System.out.println("Failed to add airport");
        }
    }
}
