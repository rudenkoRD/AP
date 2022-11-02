package lab6.menu;

public class ExitCommand implements MenuCommand {
    @Override
    public void execute() {}

    @Override
    public String menuOptionString() {
        return  "Exit";
    }
}
