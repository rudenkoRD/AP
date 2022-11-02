package lab6.menu.schedule;

import lab6.controllers.ScheduleController;
import lab6.menu.MenuCommand;

public class AddFlightCommand implements MenuCommand {
    final ScheduleController controller;

    public AddFlightCommand(ScheduleController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.addFlightToSchedule();
    }

    @Override
    public String menuOptionString() {
        return "Add flight";
    }
}
