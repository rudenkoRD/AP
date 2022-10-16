package lab5.commands;

import lab5.menu.Menu;

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
