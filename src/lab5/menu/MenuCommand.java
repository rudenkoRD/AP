package lab5.menu;

import lab5.commands.Command;

public interface MenuCommand extends Command {
    String menuOptionString();
}
