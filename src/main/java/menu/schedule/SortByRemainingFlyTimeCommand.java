package menu.schedule;

import controllers.ScheduleController;
import menu.MenuCommand;

public class SortByRemainingFlyTimeCommand implements MenuCommand {
    final ScheduleController controller;

    public SortByRemainingFlyTimeCommand(ScheduleController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.getRemainingFlyTime();
    }

    @Override
    public String menuOptionString() {
        return "Sort planes by remaining fly time";
    }
}
