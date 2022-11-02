package db.schedule;

import model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository {
    List<Flight> loadSchedule();

    void addFlight(Flight flight);

    void updateFlight(int id, LocalDateTime endTime);
}
