package menu;

import commands.Command;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Menu {
    private final InputStream in;
    private final PrintStream out;
    private final MenuCommand[] menuOptions;

    public Menu(MenuCommand[] menuOptions, InputStream in, PrintStream out) {
        this.menuOptions = menuOptions;
        this.in = in;
        this.out = out;
    }

    public void startShowing() {
        printMenu();
    }

    private void printMenu() {
        out.println("===========Menu===========");
        for (int i = 0; i < menuOptions.length; i++) {
            out.println((i + 1) + ". " + menuOptions[i].menuOptionString());
        }

        handleMenuInput();
    }

    private void handleMenuInput() {
        Scanner input = new Scanner(in);
        Command cmd = null;

        String menuItem = input.nextLine();
        try {
            int menuNum = Integer.parseInt(menuItem) - 1;
            cmd = menuOptions[menuNum];
            cmd.execute();
        } catch (Exception e) {
            out.println("Entered invalid menu number! Please try again.\n");
        } finally {
            if (!(cmd instanceof ExitCommand)) printMenu();
        }
    }
}
