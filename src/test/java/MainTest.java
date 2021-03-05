import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by RiVeRx on 05.03.2021.
 */
public class MainTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void mainNoArgs() {
        String[] args = new String[0];
        Main.main(args);
        String expected = "Please provide the .asm file to translate.\r\nExample: java MLAssembler test.asm";
        Assert.assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void mainBadFileFormat() {
        String[] args = {"test.xxx"};
        Main.main(args);
        String expected = "Wrong file format, please provide the .asm file";
        Assert.assertEquals(expected, outputStream.toString().trim());
    }
}