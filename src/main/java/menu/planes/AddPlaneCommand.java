package menu.planes;

import controllers.PlanesController;
import menu.MenuCommand;

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
