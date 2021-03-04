import java.util.HashMap;
import java.util.Map;

/**
 * Created by RiVeRx on 04.03.2021.
 */
public class SymbolTable {
    private Map<String, Integer> table;

    public SymbolTable() {
        this.table = new HashMap<String, Integer>();
        init();
    }

    private void init() {
        for (int i = 0; i < 16; i++)
            table.put("R"+ i, i);
        table.put("SCREEN", 16384);
        table.put("KBD", 24576);
        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);
    }

    public void addEntry(String symbol, int address) {
        if (!isContains(symbol))
            table.put(symbol, address);
    }

    public boolean isContains(String symbol) {
        return table.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        return table.get(symbol);
    }
}
