import java.util.ArrayList;
import java.util.List;

/**
 * Created by RiVeRx on 04.03.2021.
 */
public class Parser {
    private int count;
    private int freeN;
    private int size;
    private int codeCount;
    private SymbolTable table;
    private List<String> lines;
    public List<String> code;

    enum CommandType {
        A_COMMAND,
        C_COMMAND,
        L_COMMAND
    }

    public Parser(List<String> lines) {
        this.lines = lines;
        this.size = lines.size();
        this.count = 0;
        this.codeCount = 0;
        this.freeN = 16;
        this.code = new ArrayList<>();
        this.table = new SymbolTable();
    }

    public boolean parse() {
        while (hasNext()) {
            String line = getNext().trim();
            line = ignoreComments(line);
            if (line.length() == 0) continue; // Ignore white space and empty lines.
            CommandType type = getType(line);
            switch (type) {
                case L_COMMAND: symbol(line, type); continue; // Not need to increment codeCount, cause it's label
                default: break; // It's probably C_COMMAND or A_COMMAND which will be computed in second run.
            }
            codeCount++;
        }
        // second run
        count = 0;
        codeCount = 0;
        while (hasNext()) {
            String line = getNext().trim();
            line = ignoreComments(line);
            if (line.length() == 0) continue; // Ignore white space and empty lines.
            CommandType type = getType(line);
            switch (type) {
                case A_COMMAND: code.add(symbol(line, type)); break;
                case C_COMMAND: code.add(processC(line)); break; // dest=comp;jump
                default: break;
            }
            codeCount++;
        }
        return true;
    }

    private CommandType getType(String line) {
        if (line.startsWith("@")) return CommandType.A_COMMAND;
        if (line.startsWith("(")) return CommandType.L_COMMAND;
        return CommandType.C_COMMAND;
    }

    private String getNext() {
        return lines.get(count++);
    }

    private boolean hasNext() {
        return count < size;
    }

    private String processC(String line) {
        String dest = dest(line);
        String comp = comp(line);
        String jump = jump(line);
        return "111" + comp + dest + jump;
    }

    private String dest(String line) {
        if (line.contains("=")) {
            return Code.dest(line.substring(0, line.indexOf("=")).trim());
        } else {
            return Code.dest("null");
        }
    }

    private String comp(String line) {
        if (line.contains("=")) {
            if (line.contains(";")) {
                return Code.comp(line.substring(line.indexOf("=") + 1, line.indexOf(";")).trim().replaceAll(" ",""));
            } else {
                return Code.comp(line.substring(line.indexOf("=") + 1).trim().replaceAll(" ",""));
            }
        } else if (line.contains(";")) {
            return Code.comp(line.substring(0, line.indexOf(";")).trim());
        } else {
            return Code.comp(line);
        }
    }

    private String jump(String line) {
        if (line.contains(";")) {
            return Code.jump(line.substring(line.indexOf(";") + 1).trim());
        }
        return Code.jump("NOJUMP");
    }

    private String symbol(String line, CommandType type) {
        if (type == CommandType.L_COMMAND) {
            String name = line.substring(1, line.indexOf(")"));
            table.addEntry(name, codeCount);
            return "";
        } else {
            // A_COMMAND: @12 or @var
            try {
                // this is number.
                int num = Integer.parseInt(line.substring(1));
                return String.format("%16s", Integer.toBinaryString(num)).replaceAll(" ", "0");
            } catch (NumberFormatException e) {
                // this is var.
                String var = line.substring(1);
                if (table.isContains(var)) {
                    String address = Integer.toBinaryString(table.getAddress(var));
                    return String.format("%16s", address).replaceAll(" ", "0");
                } else {
                    table.addEntry(var, freeN);
                    return String.format("%16s", Integer.toBinaryString(freeN++)).replaceAll(" ", "0");
                }
            }
        }
    }

    private String ignoreComments(String line) {
        if (line.contains("//")) {
            return line.substring(0, line.indexOf("//")).trim();
        }
        if (line.startsWith("//")) return "";
        return line;
    }
}
