package menu.planes;

import controllers.PlanesController;
import menu.MenuCommand;

import java.time.Clock;

public class GetPlanesStatusesCommand implements MenuCommand {
    final PlanesController controller;

    public GetPlanesStatusesCommand(PlanesController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.getPlanesStatuses(Clock.systemDefaultZone());
    }

    @Override
    public String menuOptionString() {
        return "Get planes statuses(on ground/flying)";
    }
}
