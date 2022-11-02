package menu.schedule;

import controllers.ScheduleController;
import db.plane.AppPlanesRepository;
import db.schedule.AppScheduleRepository;
import menu.ExitCommand;
import menu.Menu;
import menu.MenuCommand;

public class ScheduleManagementMenuCommand implements MenuCommand {
    final ScheduleController controller = new ScheduleController(new AppScheduleRepository(), new AppPlanesRepository());

    @Override
    public void execute() {
        Menu menu = new Menu(new MenuCommand[]{
                new AddFlightCommand(controller),
                new UpdateFlightCommand(controller),
                new SortByRemainingFlyTimeCommand(controller),
                new ExitCommand()
        });
        menu.startShowing();
    }

    @Override
    public String menuOptionString() {
        return "Schedule management";
    }
}
