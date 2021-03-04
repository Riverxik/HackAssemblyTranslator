import org.junit.Assert;
import org.junit.Test;
/**
 * Created by RiVeRx on 04.03.2021.
 */
public class SymbolTableTest {

    @Test
    public void addEntry() {
        SymbolTable table = new SymbolTable();
        String symbol = "test";
        table.addEntry(symbol, 16);
        Assert.assertEquals(16, table.getAddress(symbol));
        Assert.assertTrue(table.isContains(symbol));
    }

    @Test
    public void isContains() {
        SymbolTable table = new SymbolTable();
        String symbol = "test";
        table.addEntry(symbol, 16);
        Assert.assertTrue(table.isContains(symbol));
    }

    @Test
    public void getAddress() {
        SymbolTable table = new SymbolTable();
        String symbol = "test";
        table.addEntry(symbol, 16);
        Assert.assertEquals(16, table.getAddress(symbol));
    }
}