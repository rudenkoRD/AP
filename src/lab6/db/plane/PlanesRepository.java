package lab6.db.plane;

import lab6.model.Plane;

import java.util.List;

public interface PlanesRepository {
    List<Plane> readPlanes();
    void addPlane(Plane newPlane);
}
