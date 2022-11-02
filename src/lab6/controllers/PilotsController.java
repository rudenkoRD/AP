package lab6.controllers;

import lab6.db.schedule.ScheduleRepository;
import lab6.model.Flight;
import lab6.model.Pilot;
import lab6.db.pilot.PilotsRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PilotsController {
    private final PilotsRepository repository;
    private final ScheduleRepository scheduleRepository;

    public PilotsController(PilotsRepository repository, ScheduleRepository scheduleRepository) {
        this.repository = repository;
        this.scheduleRepository = scheduleRepository;
    }

    public void addPilot() {
        try {
            repository.addPilot(new Pilot());
        } catch (Exception e) {
            System.out.println("Failed to add pilot");
        }
    }

    public void showPilotStatuses() {
        List<Pilot> pilots = repository.readPilots();

        List<Flight> flights = scheduleRepository.loadSchedule();

        pilots.forEach(pilot -> {
            List<Flight> currentPilotFlights = flights.stream().filter(flight -> {
                if (!flight.isNow()) return false;

                int[] ids = flight.getPilotIds();
                for (int id : ids) if (id == pilot.getId()) return true;
                return false;
            }).collect(Collectors.toList());

            if (currentPilotFlights.isEmpty()) System.out.printf("Pilot #%d is not on flight\n", pilot.getId());
            else System.out.printf("Pilot #%d is on flight\n", pilot.getId());
        });
    }

    public void sortPilotsByAge() {
        List<Pilot> pilots = repository.readPilots();

        pilots.sort(Comparator.comparing(Pilot::age));

        pilots.forEach(System.out::println);
    }

    public void sortPilotsBySalary() {
        List<Pilot> pilots = repository.readPilots();

        pilots.sort(Comparator.comparing(Pilot::getSalary));

        pilots.forEach(System.out::println);
    }
}
