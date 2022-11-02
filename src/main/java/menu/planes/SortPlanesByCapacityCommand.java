package menu.planes;

import controllers.PlanesController;
import menu.MenuCommand;

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
