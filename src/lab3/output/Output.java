package lab3.output;

public class Output {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[97m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[48;2;50;61;61m";

    public static String health(int health, double persent) {
        if (persent >= 80.0)
            return ANSI_GREEN + health + ANSI_WHITE;
        if (persent >= 30.0)
            return ANSI_YELLOW + health + ANSI_WHITE;
        return ANSI_RED + health + ANSI_WHITE;
    }
}
