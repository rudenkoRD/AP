package menu;

import commands.Command;
import java.util.Scanner;

public class Menu {
    private final MenuCommand[] menuOptions;
    private Scanner input;

    public Menu(MenuCommand[] menuOptions) {
        this.menuOptions = menuOptions;
    }

    public void startShowing() {
        input = new Scanner(System.in);
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
        Command cmd = null;

        if(!input.hasNextLine()) return;

        String menuItem = input.nextLine();
        try {
            int menuNum = Integer.parseInt(menuItem) - 1;
            cmd = menuOptions[menuNum];
            cmd.execute();
        } catch (Exception e) {
            System.out.println("Entered invalid menu number! Please try again.\n");
        }

        if (!(cmd instanceof ExitCommand)) printMenu();
    }
}
