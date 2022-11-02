package lab6;

import lab6.commands.Command;
import lab6.commands.OpenMenuCommand;
import lab6.menu.*;
import lab6.menu.airports.AirportsManagementMenuCommand;
import lab6.menu.pilots.PilotsManagementMenuCommand;
import lab6.menu.planes.PlanesManagementMenuCommand;
import lab6.menu.schedule.ScheduleManagementMenuCommand;

public class Lab6 {
    public static void main(String[] args) {
        Menu menu = new Menu(new MenuCommand[]{
                new PilotsManagementMenuCommand(),
                new PlanesManagementMenuCommand(),
                new AirportsManagementMenuCommand(),
                new ScheduleManagementMenuCommand(),
                new DocumentationMenuCommand(),
                new ExitCommand()
        });
        Command openMenuCommand = new OpenMenuCommand(menu);
        new App(openMenuCommand).startApp();
    }
}
