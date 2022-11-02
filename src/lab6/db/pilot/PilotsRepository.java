package lab6.db.pilot;

import lab6.model.Pilot;

import java.util.List;

public interface PilotsRepository {
    List<Pilot> readPilots();

    void addPilot(Pilot pilot);
}
