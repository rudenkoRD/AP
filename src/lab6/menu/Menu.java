package lab6.menu;

import lab6.commands.Command;
import org.jetbrains.annotations.Nullable;

import java.util.Scanner;

public class Menu {
    private final MenuCommand[] menuOptions;

    public Menu(MenuCommand[] menuOptions) {
        this.menuOptions = menuOptions;
    }

    public void startShowing() {
        printMenu();
    }


    private void printMenu() {
        System.out.println("===========Menu===========");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ". " + menuOptions[i].menuOptionString());
        }

        handleMenuInput();
    }

    private void handleMenuInput() {
        Scanner input = new Scanner(System.in);
        Command cmd = null;

        String menuItem = input.nextLine();
        try {
            int menuNum = Integer.parseInt(menuItem) - 1;
            cmd = menuOptions[menuNum];
            cmd.execute();
        } catch (Exception e) {
            System.out.println("Entered invalid menu number! Please try again.\n");
        } finally {
            if (!(cmd instanceof ExitCommand)) printMenu();
        }
    }
}
