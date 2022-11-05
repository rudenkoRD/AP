package menu;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.setIn;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MenuTest {
    MenuCommand exitCommand;

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @Test
    public void stopsShowingMenuOnExitCommand(){
        exitCommand = mock(ExitCommand.class);
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        when(exitCommand.menuOptionString()).thenReturn("Exit");

        Menu testMenu = new Menu(new MenuCommand[]{exitCommand});
        setIn(inputStream);
        testMenu.startShowing();

        verify(exitCommand, times(1)).execute();
    }

    @Test
    public void showsMenuUtilsExitCommand(){
        exitCommand = mock(ExitCommand.class);
        MenuCommand command = mock(MenuCommand.class);
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n2".getBytes());

        when(command.menuOptionString()).thenReturn("Test");
        when(exitCommand.menuOptionString()).thenReturn("Exit");

        Menu testMenu = new Menu(new MenuCommand[]{command, exitCommand});
        setIn(inputStream);
        testMenu.startShowing();

        verify(command, times(1)).execute();
        verify(exitCommand, times(1)).execute();
    }

    @Test
    public void printsErrorMessage(){
        MenuCommand command = mock(MenuCommand.class);
        ByteArrayInputStream inputStream = new ByteArrayInputStream("error\n".getBytes());
        when(command.menuOptionString()).thenReturn("Command");

        Menu testMenu = new Menu(new MenuCommand[]{command});
        setIn(inputStream);
        testMenu.startShowing();

        assertEquals("Entered invalid menu number! Please try again.", testOut.toString().split("\n")[2]);
    }
}
