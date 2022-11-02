package menu.pilots;

import controllers.PilotsController;
import menu.MenuCommand;

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
