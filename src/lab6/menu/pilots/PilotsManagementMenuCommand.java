package lab6.menu.pilots;

import lab6.controllers.PilotsController;
import lab6.db.pilot.AppPilotsRepository;
import lab6.db.schedule.AppScheduleRepository;
import lab6.menu.ExitCommand;
import lab6.menu.Menu;
import lab6.menu.MenuCommand;

public class PilotsManagementMenuCommand implements MenuCommand {
    final PilotsController controller = new PilotsController(new AppPilotsRepository(), new AppScheduleRepository());

    @Override
    public void execute() {
        Menu menu = new Menu(new MenuCommand[]{
                new AddPilotCommand(controller),
                new GetPilotsStatusesCommand(controller),
                new SortPilotsByAge(controller),
                new SortPilotsBySalary(controller),
                new ExitCommand()
        });
        menu.startShowing();
    }

    @Override
    public String menuOptionString() {
        return "Pilots management";
    }
}
