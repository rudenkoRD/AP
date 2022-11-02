package menu.planes;

import controllers.PlanesController;
import menu.MenuCommand;

public class GetPlanesStatusesCommand implements MenuCommand {
    final PlanesController controller;

    public GetPlanesStatusesCommand(PlanesController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.getPlanesStatuses();
    }

    @Override
    public String menuOptionString() {
        return "Get planes statuses(on ground/flying)";
    }
}
