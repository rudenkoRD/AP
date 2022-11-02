package lab6.menu.planes;

import lab6.controllers.PlanesController;
import lab6.menu.MenuCommand;

public class SortPlanesByCapacityCommand implements MenuCommand {
    final PlanesController controller;

    public SortPlanesByCapacityCommand(PlanesController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.sortPlanesByCapacity();
    }

    @Override
    public String menuOptionString() {
        return "Sort planes by capacity";
    }
}
