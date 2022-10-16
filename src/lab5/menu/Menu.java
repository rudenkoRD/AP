package lab5.menu;

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

        String menuItem = input.nextLine();
        try {
            int menuNum = Integer.parseInt(menuItem) - 1;

            menuOptions[menuNum].execute();
        } catch (Exception e) {
            System.out.println("Entered invalid menu number! Please try again.\n");
        } finally {
            printMenu();
        }
    }
}
