package menu.airports;

import controllers.AirportsController;
import persistence.airport.AppAirportsRepository;
import persistence.schedule.AppScheduleRepository;
import menu.ExitCommand;
import menu.Menu;
import menu.MenuCommand;

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
