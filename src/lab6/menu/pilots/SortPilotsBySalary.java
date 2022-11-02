package lab6.menu.pilots;

import lab6.controllers.PilotsController;
import lab6.menu.MenuCommand;

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
