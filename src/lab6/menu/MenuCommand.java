package lab6.menu;

import lab6.commands.Command;

public interface MenuCommand extends Command {
    String menuOptionString();
}
