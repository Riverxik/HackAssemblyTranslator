// Sample program on Hack assembler lang
// multiply two numbers
// RAM[2] = RAM[0] * RAM[1]
// Author: RiVeRx, Date: 04.03.21

// Clear possible result cache from last iteration.
@R2
M = 0
// get first operand
@R0
D = M
// if (i == 0) { goto end; } // Actually i = first operand
(IF)
@i
M = D
M;JEQ

(LOOP)
// Get second operand
@R1
D = M
// Calculate R2+=R2;
@R2
M = M + D
// Decrement i (first operand);
@i
D = M
D = D - 1
M = D
// goto if
@IF
0;JMP
// end of the program.
(END)
@END
0;JMP