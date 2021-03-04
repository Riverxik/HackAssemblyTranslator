import org.junit.Assert;

/**
 * Created by RiVeRx on 04.03.2021.
 */
public class CodeTest {

    @org.junit.Test
    public void dest() {
        Assert.assertEquals("001", Code.dest("M"));
        Assert.assertEquals("010", Code.dest("D"));
        Assert.assertEquals("011", Code.dest("MD"));
        Assert.assertEquals("100", Code.dest("A"));
        Assert.assertEquals("101", Code.dest("AM"));
        Assert.assertEquals("110", Code.dest("AD"));
        Assert.assertEquals("111", Code.dest("AMD"));
        Assert.assertEquals("000", Code.dest(""));
    }

    @org.junit.Test
    public void comp() {
        Assert.assertEquals("0101010", Code.comp("0"));
        Assert.assertEquals("0111111", Code.comp("1"));
        Assert.assertEquals("0111010", Code.comp("-1"));
        Assert.assertEquals("0001100", Code.comp("D"));
        Assert.assertEquals("0110000", Code.comp("A"));
        Assert.assertEquals("1110000", Code.comp("M"));
        Assert.assertEquals("0001101", Code.comp("!D"));
        Assert.assertEquals("0110001", Code.comp("!A"));
        Assert.assertEquals("1110001", Code.comp("!M"));
        Assert.assertEquals("0001111", Code.comp("-D"));
        Assert.assertEquals("0110011", Code.comp("-A"));
        Assert.assertEquals("1110011", Code.comp("-M"));
        Assert.assertEquals("0011111", Code.comp("D+1"));
        Assert.assertEquals("0110111", Code.comp("A+1"));
        Assert.assertEquals("1110111", Code.comp("M+1"));
        Assert.assertEquals("0001110", Code.comp("D-1"));
        Assert.assertEquals("0110010", Code.comp("A-1"));
        Assert.assertEquals("1110010", Code.comp("M-1"));
        Assert.assertEquals("0000010", Code.comp("D+A"));
        Assert.assertEquals("1000010", Code.comp("D+M"));
        Assert.assertEquals("0010011", Code.comp("D-A"));
        Assert.assertEquals("1010011", Code.comp("D-M"));
        Assert.assertEquals("0000111", Code.comp("A-D"));
        Assert.assertEquals("1000111", Code.comp("M-D"));
        Assert.assertEquals("0000000", Code.comp("D&A"));
        Assert.assertEquals("1000000", Code.comp("D&M"));
        Assert.assertEquals("0010101", Code.comp("D|A"));
        Assert.assertEquals("1010101", Code.comp("D|M"));
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void compException() {
        Code.comp("This will throw an exception");
    }

    @org.junit.Test
    public void jump() {
        Assert.assertEquals("001", Code.jump("JGT"));
        Assert.assertEquals("010", Code.jump("JEQ"));
        Assert.assertEquals("011", Code.jump("JGE"));
        Assert.assertEquals("100", Code.jump("JLT"));
        Assert.assertEquals("101", Code.jump("JNE"));
        Assert.assertEquals("110", Code.jump("JLE"));
        Assert.assertEquals("111", Code.jump("JMP"));
        Assert.assertEquals("000", Code.jump("JJJ"));
    }
}