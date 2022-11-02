package lab6.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, dateFormat);
    }

    public static String formatDate(LocalDate date) {
        return date.format(dateFormat);
    }

    public static LocalDateTime parseTime(String date) {
        return LocalDateTime.parse(date, timeFormat);
    }

    public static String formatTime(LocalDateTime date) {
        return date.format(timeFormat);
    }
}
