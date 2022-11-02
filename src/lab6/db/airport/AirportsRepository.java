package lab6.db.airport;

import lab6.model.Airport;

import java.util.List;

public interface AirportsRepository {
    List<Airport> readAirports();

    void addAirport(Airport airport);
}
