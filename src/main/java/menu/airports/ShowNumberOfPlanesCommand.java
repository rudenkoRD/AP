package menu.airports;

import controllers.AirportsController;
import menu.MenuCommand;

public class ShowNumberOfPlanesCommand implements MenuCommand {
    AirportsController controller;

    public ShowNumberOfPlanesCommand(AirportsController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.getNumberOfPlanesInAirports();
    }

    @Override
    public String menuOptionString() {
        return "Show number of planes in airports";
    }
}
