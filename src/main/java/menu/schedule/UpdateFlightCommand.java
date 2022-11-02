package menu.schedule;

import controllers.ScheduleController;
import menu.MenuCommand;

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
