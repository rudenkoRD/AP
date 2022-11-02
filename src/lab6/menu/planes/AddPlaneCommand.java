package lab6.menu.planes;

import lab6.controllers.PlanesController;
import lab6.menu.MenuCommand;

public class AddPlaneCommand implements MenuCommand {
    final PlanesController controller;

    public AddPlaneCommand(PlanesController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.addPlane();
    }

    @Override
    public String menuOptionString() {
        return "Add plane";
    }
}
