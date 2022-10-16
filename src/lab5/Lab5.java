package lab5;

import lab5.commands.Command;
import lab5.commands.OpenMenuCommand;
import lab5.menu.*;

public class Lab5 {
    public static void main(String[] args) {
        Menu menu = new Menu(new MenuCommand[]{
                new PilotsManagementMenuCommand(),
                new PlanesManagementMenuCommand(),
                new TerminalsManagementMenuCommand(),
                new ScheduleManagementMenuCommand(),
                new DocumentationMenuCommand(),
                new ExitMenuCommand()
        });
        Command openMenuCommand = new OpenMenuCommand(menu);
        new App(openMenuCommand).startApp();
    }
}
