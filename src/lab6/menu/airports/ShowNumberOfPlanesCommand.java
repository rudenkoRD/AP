package lab6.menu.airports;

import lab6.controllers.AirportsController;
import lab6.menu.MenuCommand;

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
