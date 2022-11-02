package db.plane;

import model.Plane;

import java.util.List;

public interface PlanesRepository {
    List<Plane> readPlanes();

    void addPlane(Plane newPlane);
}
