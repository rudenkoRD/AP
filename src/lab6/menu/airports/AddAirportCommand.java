package lab6.menu.airports;

import lab6.controllers.AirportsController;
import lab6.menu.MenuCommand;

public class AddAirportCommand implements MenuCommand {
    AirportsController controller;

    public AddAirportCommand(AirportsController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.addAirport();
    }

    @Override
    public String menuOptionString() {
        return "Add airport";
    }
}
