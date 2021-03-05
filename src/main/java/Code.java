/**
 * Created by RiVeRx on 04.03.2021.
 */
public class Code {
    static String dest(String code) {
        if (code.equals("M"))    return "001";
        if (code.equals("D"))    return "010";
        if (code.equals("MD"))   return "011";
        if (code.equals("A"))    return "100";
        if (code.equals("AM"))   return "101";
        if (code.equals("AD"))   return "110";
        if (code.equals("AMD"))  return "111";
        return "000";
    }

    static String comp(String code) {
        if (code.equals("0"))                           return "0101010";
        if (code.equals("1"))                           return "0111111";
        if (code.equals("-1"))                          return "0111010";
        if (code.equals("D"))                           return "0001100";
        if (code.equals("A"))                           return "0110000";
        if (code.equals("M"))                           return "1110000";
        if (code.equals("!D"))                          return "0001101";
        if (code.equals("!A"))                          return "0110001";
        if (code.equals("!M"))                          return "1110001";
        if (code.equals("-D"))                          return "0001111";
        if (code.equals("-A"))                          return "0110011";
        if (code.equals("-M"))                          return "1110011";
        if (code.equals("D+1") || code.equals("1+D"))   return "0011111";
        if (code.equals("A+1") || code.equals("1+A"))   return "0110111";
        if (code.equals("M+1") || code.equals("1+M"))   return "1110111";
        if (code.equals("D-1"))                         return "0001110";
        if (code.equals("A-1"))                         return "0110010";
        if (code.equals("M-1"))                         return "1110010";
        if (code.equals("D+A") || code.equals("A+D"))   return "0000010";
        if (code.equals("D+M") || code.equals("M+D"))   return "1000010";
        if (code.equals("D-A"))                         return "0010011";
        if (code.equals("D-M"))                         return "1010011";
        if (code.equals("A-D"))                         return "0000111";
        if (code.equals("M-D"))                         return "1000111";
        if (code.equals("D&A") || code.equals("A&D"))   return "0000000";
        if (code.equals("D&M") || code.equals("M&D"))   return "1000000";
        if (code.equals("D|A") || code.equals("A|D"))   return "0010101";
        if (code.equals("D|M") || code.equals("M|D"))   return "1010101";
        throw new IllegalArgumentException("No type comp for: " + code);
    }

    static String jump(String code) {
        if (code.equals("JGT")) return "001";
        if (code.equals("JEQ")) return "010";
        if (code.equals("JGE")) return "011";
        if (code.equals("JLT")) return "100";
        if (code.equals("JNE")) return "101";
        if (code.equals("JLE")) return "110";
        if (code.equals("JMP")) return "111";
        return "000";
    }
}
