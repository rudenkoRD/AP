package lab6.menu.pilots;

import lab6.controllers.PilotsController;
import lab6.menu.MenuCommand;

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
