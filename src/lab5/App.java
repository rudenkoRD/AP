package lab5;

import lab5.commands.Command;

public class App {
    private final Command openAppCommand;

    public App(Command openAppCommand) {
        this.openAppCommand = openAppCommand;
    }

    public void startApp() {
        openAppCommand.execute();
    }
}
