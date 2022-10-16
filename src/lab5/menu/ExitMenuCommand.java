package lab5.menu;

public class ExitMenuCommand implements MenuCommand {
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String menuOptionString() {
        return  "Exit";
    }
}
