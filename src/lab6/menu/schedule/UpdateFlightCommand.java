package lab6.menu.schedule;

import lab6.controllers.ScheduleController;
import lab6.menu.MenuCommand;

public class UpdateFlightCommand implements MenuCommand {
    final ScheduleController controller;

    public UpdateFlightCommand(ScheduleController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.updateFlight();
    }

    @Override
    public String menuOptionString() {
        return "Update flight";
    }
}
