import menu.DocumentationMenuCommand;
import menu.ExitCommand;
import menu.Menu;
import menu.MenuCommand;
import menu.airports.AirportsManagementMenuCommand;
import menu.pilots.PilotsManagementMenuCommand;
import menu.planes.PlanesManagementMenuCommand;
import menu.schedule.ScheduleManagementMenuCommand;

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
        menu.startShowing();
    }
}
