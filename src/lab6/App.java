package lab6;

import lab6.commands.Command;

public class App {
    private final Command openAppCommand;

    public App(Command openAppCommand) {
        this.openAppCommand = openAppCommand;
    }

    public void startApp() {
        openAppCommand.execute();
    }
}
