package lab6.menu.planes;

import lab6.controllers.PlanesController;
import lab6.db.plane.AppPlanesRepository;
import lab6.db.schedule.AppScheduleRepository;
import lab6.menu.ExitCommand;
import lab6.menu.Menu;
import lab6.menu.MenuCommand;

public class PlanesManagementMenuCommand implements MenuCommand {
    final PlanesController controller = new PlanesController(new AppPlanesRepository(), new AppScheduleRepository());

    @Override
    public void execute() {
        Menu menu = new Menu(new MenuCommand[]{
                new AddPlaneCommand(controller),
                new GetPlanesStatusesCommand(controller),
                new SortPlanesByCapacityCommand(controller),
                new SortPlanesByFlightDistance(controller),
                new ExitCommand()
        });
        menu.startShowing();
    }

    @Override
    public String menuOptionString() {
        return "Planes management";
    }
}
