package db.airport;

import model.Airport;

import java.util.List;

public interface AirportsRepository {
    List<Airport> readAirports();

    void addAirport(Airport airport);
}
