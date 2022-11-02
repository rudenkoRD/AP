package lab6.menu.schedule;

import lab6.controllers.ScheduleController;
import lab6.db.plane.AppPlanesRepository;
import lab6.db.schedule.AppScheduleRepository;
import lab6.menu.ExitCommand;
import lab6.menu.Menu;
import lab6.menu.MenuCommand;

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
