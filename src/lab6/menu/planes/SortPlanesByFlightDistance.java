package lab6.menu.planes;

import lab6.controllers.PlanesController;
import lab6.menu.MenuCommand;

public class SortPlanesByFlightDistance implements MenuCommand {
    final PlanesController controller;

    public SortPlanesByFlightDistance(PlanesController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.sortPlanesByAvailableDistance();
    }

    @Override
    public String menuOptionString() {
        return "Sort planes by maximum flight distance";
    }
}
