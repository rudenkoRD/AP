package persistence.pilot;

import model.Pilot;

import java.util.List;

public interface PilotsRepository {
    List<Pilot> readPilots();

    void addPilot(Pilot pilot);
}
