package menu.pilots;

import controllers.PilotsController;
import persistence.pilot.AppPilotsRepository;
import persistence.schedule.AppScheduleRepository;
import menu.ExitCommand;
import menu.Menu;
import menu.MenuCommand;

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
