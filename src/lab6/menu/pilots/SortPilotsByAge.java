package lab6.menu.pilots;

import lab6.controllers.PilotsController;
import lab6.menu.MenuCommand;

public class SortPilotsByAge implements MenuCommand {
    PilotsController controller;

    public SortPilotsByAge(PilotsController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.sortPilotsByAge();
    }

    @Override
    public String menuOptionString() {
        return "Sort pilots by age";
    }
}
