package menu.pilots;

import controllers.PilotsController;
import menu.MenuCommand;

public class SortPilotsBySalary implements MenuCommand {
    PilotsController controller;

    public SortPilotsBySalary(PilotsController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.sortPilotsBySalary();
    }

    @Override
    public String menuOptionString() {
        return "Sort pilots by salary";
    }
}
