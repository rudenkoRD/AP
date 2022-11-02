package menu;

import commands.Command;

public interface MenuCommand extends Command {
    String menuOptionString();
}
