package menu.pilots;

import controllers.PilotsController;
import menu.MenuCommand;

public class GetPilotsStatusesCommand implements MenuCommand {
    final PilotsController controller;

    public GetPilotsStatusesCommand(PilotsController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.showPilotStatuses();
    }

    @Override
    public String menuOptionString() {
        return "Get pilots statuses(in flight/on ground)";
    }
}
