package lab6.controllers;

import lab6.db.plane.PlanesRepository;
import lab6.db.schedule.ScheduleRepository;
import lab6.model.Flight;
import lab6.model.Plane;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlanesController {
    private final PlanesRepository repository;
    private final ScheduleRepository scheduleRepository;

    public PlanesController(PlanesRepository repository, ScheduleRepository scheduleRepository) {
        this.repository = repository;
        this.scheduleRepository = scheduleRepository;
    }

    public void getPlanesStatuses() {
        List<Plane> planes = repository.readPlanes();
        List<Flight> flights = scheduleRepository.loadSchedule();

        planes.forEach(plane -> {
            List<Flight> currentPlaneFlights = flights.stream().filter(flight -> {
                if (!flight.isNow()) return false;
                return flight.getPlaneId() == plane.getId();
            }).collect(Collectors.toList());

            if (currentPlaneFlights.isEmpty()) System.out.printf("Plan #%d is not flying\n", plane.getId());
            else System.out.printf("Plan #%d is flying\n", plane.getId());
        });
    }

    public void addPlane() {
        try {
            repository.addPlane(new Plane());
        } catch (Exception e) {
            System.out.println("Failed to add plane");
        }
    }

    public void sortPlanesByCapacity() {
        List<Plane> planeList = repository.readPlanes();
        planeList.sort(Comparator.comparing(Plane::getCapacity));
        planeList.forEach(System.out::println);
    }

    public void sortPlanesByAvailableDistance() {
        List<Plane> planeList = repository.readPlanes();
        planeList.sort(Comparator.comparing(Plane::getMaxFlightDistance));
        planeList.forEach(System.out::println);
    }
}
