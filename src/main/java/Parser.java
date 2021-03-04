import java.util.ArrayList;
import java.util.List;

/**
 * Created by RiVeRx on 04.03.2021.
 */
public class Parser {
    private int count;
    private int freeN;
    private int size;
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
        this.freeN = 16;
        this.code = new ArrayList<>();
        this.table = new SymbolTable();
    }

    public boolean parse() {
        while (hasNext()) {
            String line = getNext().trim();
            line = ignoreComments(line);
            CommandType type = getType(line);
            switch (type) {
                case L_COMMAND:
                case A_COMMAND: code.add(symbol(line, type)); break;
                default: break; // It's probably C_COMMAND which will be computed in second run.
            }
            count++;
        }
        // second run
        count = 0;
        while (hasNext()) {
            String line = getNext().trim();
            line = ignoreComments(line);
            CommandType type = getType(line);
            switch (type) {
                case L_COMMAND:
                case A_COMMAND: code.add(symbol(line, type)); break;
                case C_COMMAND: code.add(processC(line)); break; // dest=comp;jump
                default: break;
            }
            count++;
        }
        return true;
    }

    private CommandType getType(String line) {
        if (line.startsWith("@")) return CommandType.A_COMMAND;
        if (line.startsWith("(")) return CommandType.L_COMMAND;
        return CommandType.C_COMMAND;
    }

    private String getNext() {
        return lines.get(count);
    }

    private boolean hasNext() {
        return count < size;
    }

    private String processC(String line) {
        String dest = dest(line);
        String comp = comp(line);
        String jump = jump(line);
        return "111" + dest + comp + jump;
    }

    private String dest(String line) {
        if (line.contains("=")) {
            return Code.dest(line.substring(0, line.indexOf("=")));
        } else {
            return Code.dest("null");
        }
    }

    private String comp(String line) {
        if (line.contains("=")) {
            if (line.contains(";")) {
                return Code.comp(line.substring(line.indexOf("=") + 1, line.indexOf(";")));
            } else {
                return Code.comp(line.substring(line.indexOf("=")));
            }
        } else if (line.contains(";")) {
            return Code.comp(line.substring(0, line.indexOf(";")));
        } else {
            return Code.comp(line);
        }
    }

    private String jump(String line) {
        if (line.contains(";")) {
            return Code.jump(line.substring(line.indexOf(";") + 1));
        }
        return Code.jump("JMP");
    }

    private String symbol(String line, CommandType type) {
        if (type == CommandType.L_COMMAND) {
            String name = line.substring(1, line.indexOf(")"));
            table.addEntry(name, count);
        } else {
            // A_COMMAND: @12 or @var
            if (table.isContains(line)) {
                String address = Integer.toBinaryString(table.getAddress(line));
                return String.format("%16s", address).replaceAll(" ", "0");
            } else {
                table.addEntry(line, freeN++);
            }
        }
        throw new IllegalArgumentException();
    }

    private String ignoreComments(String line) {
        if (line.contains("//")) {
            return line.substring(0, line.indexOf("//"));
        }
        if (line.startsWith("//")) return "";
        return line;
    }
}
