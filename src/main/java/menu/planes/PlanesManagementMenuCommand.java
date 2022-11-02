package menu.planes;

import controllers.PlanesController;
import db.plane.AppPlanesRepository;
import db.schedule.AppScheduleRepository;
import menu.ExitCommand;
import menu.Menu;
import menu.MenuCommand;

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
