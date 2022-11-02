package lab6.commands;

import lab6.commands.Command;
import lab6.menu.Menu;

public class OpenMenuCommand implements Command {
    private final Menu menu;

    public OpenMenuCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.startShowing();
    }
}
