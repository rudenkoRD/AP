package menu.pilots;

import controllers.PilotsController;
import menu.MenuCommand;

public class AddPilotCommand implements MenuCommand {
    final PilotsController controller;

    public AddPilotCommand(PilotsController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.addPilot();
    }

    @Override
    public String menuOptionString() {
        return "Add pilot";
    }
}
