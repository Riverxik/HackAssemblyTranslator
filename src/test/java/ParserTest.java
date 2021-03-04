import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RiVeRx on 04.03.2021.
 * Test code below
 * @17
 * D=D-A
 * M=M+1
 * D=D-1;JEQ
 */
public class ParserTest {
    private Parser parser;
    @Before
    public void init() {
        List<String> lines = new ArrayList<>();
        lines.add("@17");
        lines.add("D=D-A");
        lines.add("M=M+1");
        lines.add("D=D-1;JEQ");
        parser = new Parser(lines);
    }

    @Test
    public void parse() {
        // TODO: make test for parser
    }
}