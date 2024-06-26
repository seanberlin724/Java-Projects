 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
 public class Parser {
     
     //Add your constants here
     private static final int FLOATDCL = 0;
     private static final int INTDCL = 1;
     private static final int PRINT = 2;
     private static final int ID = 3;
     private static final int ASSIGN = 4;
     private static final int PLUS = 5;
     private static final int MINUS = 6;
     private static final int INUM = 7;
     private static final int FNUM = 8;
     private static final int EOF = -1;

     static int currentIndex = 0;
     static StringBuilder currentLexeme = new StringBuilder();
     static String currentLine;  // Current line being processed

     static String [] tokens = new String[100]; // Store tokens generated by the lexer
     
     public static void main(String[] args) {
         try {
             // Do NOT make any changes to the following TWO lines
             File file = new File(args[0]);		
             Scanner sc = new Scanner(file);		//*** Do not make any other Scanners ***//
            // Process the input file using hasNextLine and nextLine
            while (sc.hasNextLine()) {
                currentLine = sc.nextLine();
                //currentIndex = 0;
                lex();
                Prog();   
            }
             sc.close();
         } catch (FileNotFoundException e) {
             System.out.println("ERROR - cannot open i(n).txt\n");
         }
     }
     
 // lexical analysis of a single line
     private static void lex() {

        //Store tokens in the tokens array
        //tokens[currentIndex] = "f";
               
        if (currentLine != null && currentIndex < currentLine.length()) {
            char currentChar = currentLine.charAt(currentIndex);
            currentLexeme.setLength(0); // Clear the current lexeme
    
            // Skip whitespace characters
            while (currentIndex < currentLine.length() && Character.isWhitespace(currentChar)) {
                currentIndex++;
                if (currentIndex < currentLine.length()) {
                    currentChar = currentLine.charAt(currentIndex);
                }
            }
 
             // Handle letters and identifiers
             if (Character.isLetter(currentChar)) {
                 while (currentIndex < currentLine.length() &&
                         (Character.isLetterOrDigit(currentLine.charAt(currentIndex)) || currentLine.charAt(currentIndex) == '_')) {
                     currentLexeme.append(currentLine.charAt(currentIndex));
                     currentIndex++;
                 }
                 String lexemeStr = currentLexeme.toString().toLowerCase();
                 if (lexemeStr.equals("f")){ // f
                     printToken(FLOATDCL, lexemeStr);
                     tokens[currentIndex] = "FLOATDCL";
                 }
                 else if (lexemeStr.equals("i")){ // i
                     printToken(INTDCL, lexemeStr);
                     tokens[currentIndex] = "INTDCL";
                 }
                 else if (lexemeStr.equals("p")){ // p
                     printToken(PRINT, lexemeStr);
                     tokens[currentIndex] = "PRINT";
                 }
                 else {
                     for (int i = 0; i < lexemeStr.length(); i++) { // Other 23 letters:
                                                                     // a+b+c+d+e+g+h+j+k+l+m+n+o+q+r+s+t+u+v+w+x+y+z
                         printToken(ID, String.valueOf(lexemeStr.charAt(i)));
                         tokens[currentIndex] = "ID";
                     }
                 }
             }
             // Dealing with digits and decimals
             else if (Character.isDigit(currentChar)) {
                 boolean isFloatingPoint = false;
 
                 while (currentIndex < currentLine.length() &&
                         (Character.isDigit(currentLine.charAt(currentIndex)) || currentLine.charAt(currentIndex) == '.')) {
                     char digitChar = currentLine.charAt(currentIndex);
                     if (digitChar == '.') {
                         if (isFloatingPoint) {
                             break; // gets out of loop once consecutive decimal points is encountered
                         }
                         isFloatingPoint = true;
                     }
                     /*
                      * if (digitChar == '.') {
                      * isFloatingPoint = true;
                      * }
                      */
                     currentLexeme.append(digitChar);
                     currentIndex++;
                 }
                 if (isFloatingPoint) { // (0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9)^+.(0 + 1 + 2
                                         // + 3 + 4 + 5 + 6 + 7 + 8 + 9)^+
                     printToken(FNUM, currentLexeme.toString());
                     tokens[currentIndex] = "FNUM";
                 } else { // (0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9)^+
                     printToken(INUM, currentLexeme.toString());
                     tokens[currentIndex] = "INUM";
                 }
             }
             // Dealing with operators
             else {
                 switch (currentChar) {
                     case '=':
                         currentLexeme.append(currentChar);
                         currentIndex++;
                         printToken(ASSIGN, currentLexeme.toString());
                         tokens[currentIndex] = "ASSIGN";
                         break;
                     case '+':
                         currentLexeme.append(currentChar);
                         currentIndex++;
                         printToken(PLUS, currentLexeme.toString());
                         tokens[currentIndex] = "PLUS";
                         break;
                     case '-':
                         currentLexeme.append(currentChar);
                         currentIndex++;
                         printToken(MINUS, currentLexeme.toString());
                         tokens[currentIndex] = "MINUS";
                         break;
                     default:
                         // System.out.println("Error - Undefined or invalid symbol: " + currentChar);
                         // for testing purposes
                         System.exit(1); // Exit program when invalid lexeme is found
                 }
                }
            }else{ //when eof
				tokens[currentIndex]  = "$";
			}
         //}end while loop
     }
 
     // Method to print tokens and current lexemes
     private static void printToken(int token, String lexeme) {
         System.out.println("Next token is: " + token + ", Next lexeme is " + lexeme);
     }
 
     // Method to get the name of a token based on its ID
     private static String getTokenName(int token) {
         switch (token) {
             case FLOATDCL:
                 return "f";
             case INTDCL:
                 return "i";
             case PRINT:
                 return "p";
             case ID:
                 return "ID";
             case ASSIGN:
                 return "=";
             case PLUS:
                 return "+";
             case MINUS:
                 return "-";
             case INUM:
                 return "INUM";
             case FNUM:
                 return "FNUM";
             default:
                 return "UNKNOWN";
         }
     }
    
     /*Prog() parses strings in the language generate by the rule:
	 <Prog> → <Dcls> <Stmts>
	 */
	private static void Prog(){
		System.out.print("Enter <Prog>\n");
		if(tokens[currentIndex].equals("FLOATDCL") || tokens[currentIndex].equals("INTDCL")){
			Dcls();
		}
		Stmts();
		System.out.print("Exit <Prog>\n");
	}

	/*Dcls() parses strings in the language generate by the rule:
	 <Dcls> → <Dcl> <Dcls> | λ
	 */
	private static void Dcls(){
		if(tokens[currentIndex].equals("FLOATDCL") || tokens[currentIndex].equals("INTDCL")){
			System.out.print("Enter <Dcls>\n");
			while (tokens[currentIndex].equals("FLOATDCL") || tokens[currentIndex].equals("INTDCL")) {
            	Dcl();
				Dcls();
        	}
		System.out.print("Exit <Dcls>\n");
		}
	}

	/*Dcl() parses strings in the language generate by the rule:
	 <Dcl> → FLOATDCL ID | INTDCL ID
	 */
	private static void Dcl(){
		System.out.print("Enter <Dcl>\n");
        if (tokens[currentIndex].equals("FLOATDCL") || tokens[currentIndex].equals("INTDCL")) {
            lex();  // Match FLOATDCL or INTDCL

            // Check for ID
            if (tokens[currentIndex].equals("ID")) {
                lex();  // Match the ID
            }
        }
		System.out.print("Exit <Dcl>\n");
	}

	/*Stmts() parses strings in the language generate by the rule:
	 <Stmts> → <Stmt> <Stmts> | λ
	 */
	private static void Stmts(){
		if(tokens[currentIndex].equals("ID") || tokens[currentIndex].equals("PRINT")){
        	System.out.print("Enter <Stmts>\n");
			while (tokens[currentIndex].equals("ID") || tokens[currentIndex].equals("PRINT")) {
            	Stmt();
				Stmts();
				lex();
        	}
			System.out.print("Exit <Stmts>\n");
		}

	}

	/*Stmt() parses strings in the language generate by the rule:
	 <Stmt> → ID ASSIGN <Val> <Expr> | PRINT ID
	 */
	private static void Stmt(){
		System.out.print("Enter <Stmt>\n");
		if (tokens[currentIndex].equals("ID") || tokens[currentIndex].equals("PRINT")) {
			lex();  // Match the ID
	
			if (tokens[currentIndex].equals("ASSIGN")) {
				lex();
				Val();
				if(tokens[currentIndex].equals("PLUS")){
					Expr();
				}
			}else{
				lex();
			}
		}
		System.out.print("Exit <Stmt>\n");
	}

	/*Expr() parses strings in the language generate by the rule:
	 <Expr> → PLUS <Val> <Expr> | MINUS <Val> <Expr> | λ
	 */
	private static void Expr(){
		if(tokens[currentIndex].equals("PLUS") || tokens[currentIndex].equals("MINUS")){
			System.out.print("Enter <Expr>\n");
			while (tokens[currentIndex].equals("PLUS") || tokens[currentIndex].equals("MINUS")) {
            	lex();  // Match PLUS or MINUS
            	Val();
            	Expr();
        	}
			System.out.print("Exit <Expr>\n");
		}else{}
	}

	/*Val() parses strings in the language generate by the rule:
	 <Val> → ID | INUM | FNUM 
	 */
	private static void Val(){
        System.out.print("Enter <Val>\n");
        if (tokens[currentIndex].equals("ID") || tokens[currentIndex].equals("INUM") || tokens[currentIndex].equals("FNUM")) {
            lex();  // Match ID, INUM, or FNUM
        }
        System.out.print("Exit <Val>\n");
	}
}//end class 
