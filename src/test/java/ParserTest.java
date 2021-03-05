import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<String> lines;
    private List<String> expCode;

    @Before
    public void init() {
        expCode = new ArrayList<>();
        lines = new ArrayList<>();
    }

    @Test
    public void parseAddress() {
        lines.add("@17");
        expCode.add("0000000000010001");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseRegisters() {
        lines.add("D=D-A");
        expCode.add("1110010011010000");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseMemory() {
        lines.add("M=M+1");
        expCode.add("1111110111001000");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseJump() {
        lines.add("D=D-1;JEQ");
        expCode.add("1110001110010010");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void fullParse() {
        lines.add("@17");
        lines.add("D=D-A");
        lines.add("M=M+1");
        lines.add("D=D-1;JEQ");
        expCode.add("0000000000010001");
        expCode.add("1110010011010000");
        expCode.add("1111110111001000");
        expCode.add("1110001110010010");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseWithLabels() {
        lines.addAll(Arrays.asList("@17", "D=D-A", "@TEST", "M=M+1", "(TEST)", "D=D-1;JEQ"));
        expCode.addAll(Arrays.asList("0000000000010001", "1110010011010000", "0000000000000100",
                "1111110111001000", "1110001110010010"));
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseIgnoreLineComment() {
        lines.add("@R1");
        lines.add("// This is comment. Ignore it");
        lines.add("D = A");
        expCode.addAll(Arrays.asList("0000000000000001", "1110110000010000"));
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseIgnoreInlineComment() {
        lines.add("@R1 // This is the register number 1.");
        expCode.add("0000000000000001");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseCompWithWhiteSpaces() {
        lines.add("D = D + M");
        expCode.add("1111000010010000");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseIgnoreEmptyLines() {
        lines.add("D = D + M");
        lines.add("");
        lines.add("D = A");
        expCode.add("1111000010010000");
        expCode.add("1110110000010000");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseNewVar() {
        lines.add("D = A");
        lines.add("@i");
        lines.add("D = A");
        expCode.add("1110110000010000");
        expCode.add("0000000000010000");
        expCode.add("1110110000010000");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseRepeatVar() {
        lines.add("D = A");
        lines.add("@i");
        lines.add("D = A");
        lines.add("@i");
        lines.add("D = A");
        expCode.add("1110110000010000");
        expCode.add("0000000000010000");
        expCode.add("1110110000010000");
        expCode.add("0000000000010000");
        expCode.add("1110110000010000");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }

    @Test
    public void parseEndLoop() {
        lines.add("D = A");
        lines.add("(END)");
        lines.add("@END");
        lines.add("0;JMP");
        expCode.add("1110110000010000");
        expCode.add("0000000000000001");
        expCode.add("1110101010000111");
        parser = new Parser(lines);
        parser.parse();
        Assert.assertEquals(expCode, parser.code);
    }
}