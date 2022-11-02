package lab6.menu.airports;

import lab6.controllers.AirportsController;
import lab6.db.airport.AppAirportsRepository;
import lab6.db.schedule.AppScheduleRepository;
import lab6.menu.ExitCommand;
import lab6.menu.Menu;
import lab6.menu.MenuCommand;

public class AirportsManagementMenuCommand implements MenuCommand {
    final AirportsController controller = new AirportsController(new AppAirportsRepository(), new AppScheduleRepository());

    @Override
    public void execute() {
        showOptions();
    }

    private void showOptions() {
        Menu menu = new Menu(new MenuCommand[]{
                new AddAirportCommand(controller),
                new ShowNumberOfPlanesCommand(controller),
                new ExitCommand(),
        });
        menu.startShowing();
    }

    @Override
    public String menuOptionString() {
        return "Airports management";
    }
}
