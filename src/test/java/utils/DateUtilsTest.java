package utils;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DateUtilsTest {
    String incorrectDateTimeString = "12121212";
    String dateString = "12/01/2022";
    LocalDate date = LocalDate.of(2022, 1, 12);
    String timeString = "12-01-2022 12:22";
    LocalDateTime time = LocalDateTime.of(2022, 1, 12, 12, 22);

    @Test
    public void parsesDateCorrectly() {
        LocalDate parsedDate = DateUtils.parseDate(dateString);

        assertEquals(parsedDate, date);
    }

    @Test
    public void parseDateThrowsError() {
        assertThrows(
                DateTimeParseException.class,
                () -> DateUtils.parseDate(incorrectDateTimeString)
        );
    }

    @Test
    public void parsesTimeCorrectly() {
        LocalDateTime parsedTime = DateUtils.parseTime(timeString);

        assertEquals(parsedTime, time);
    }

    @Test
    public void parseTimeThrowsError() {
        assertThrows(
                DateTimeParseException.class,
                () -> DateUtils.parseTime(incorrectDateTimeString)
        );
    }

    @Test
    public void formatsDateCorrectly() {
        String formattedDate = DateUtils.formatDate(date);

        assertEquals(formattedDate, dateString);
    }

    @Test
    public void formatsTimeCorrectly() {
        String formattedTime = DateUtils.formatTime(time);

        assertEquals(formattedTime, timeString);
    }
}
