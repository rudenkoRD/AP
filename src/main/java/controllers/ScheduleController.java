package controllers;

import db.plane.PlanesRepository;
import db.schedule.ScheduleRepository;
import model.Flight;
import model.Plane;
import utils.DateUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ScheduleController {
    private final ScheduleRepository repository;
    private final PlanesRepository planesRepository;

    public ScheduleController(ScheduleRepository repository, PlanesRepository planesRepository) {
        this.repository = repository;
        this.planesRepository = planesRepository;
    }

    public void addFlightToSchedule() {
        try {
            repository.addFlight(new Flight(System.in, System.out));
        } catch (Exception e) {
            System.out.println("Failed to add flight");
        }
    }

    public void updateFlight() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter flight id to update: ");
            int id = in.nextInt();
            in.nextLine();
            System.out.println("Enter new end time(dd/mm/yyyy hh:mm format): ");
            String endTime = in.nextLine();
            repository.updateFlight(id, DateUtils.parseTime(endTime));
        } catch (Exception e) {
            System.out.println("Invalid data\nFailed to update flight");
        }
    }

    public void getRemainingFlyTime() {
        List<Flight> flights = repository.loadSchedule();
        List<Plane> planes = planesRepository.readPlanes();

        Map<Long, Plane> res = new TreeMap<>();

        for (Plane plane : planes) {
            long flightTime = remainingFlightTime(plane, flights);
            if (flightTime != -1) res.put(flightTime, plane);
        }

        if (res.entrySet().isEmpty()) {
            System.out.println("No flights!");
        }

        res.forEach((key, value) -> System.out.printf("Plane %d, remaining fly time %d minutes\n", value.getId(), key));
    }

    private long remainingFlightTime(Plane plane, List<Flight> flights) {
        for (Flight flight : flights) {
            LocalDateTime now = LocalDateTime.now();
            if (flight.getPlaneId() != plane.getId()) continue;

            if (flight.getEndTime().isAfter(now) && flight.getStartTime().isBefore(now)) {
                return ChronoUnit.MINUTES.between(now, flight.getEndTime());
            }
        }
        return -1;
    }
}
