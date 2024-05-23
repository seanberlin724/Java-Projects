import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LexicalAnalyzer {

	// Students -- Add your constants here
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

	public static void main(String[] args) {
		try {
			// Do NOT make any changes to the following TWO lines
			File file = new File(args[0]);
			Scanner sc = new Scanner(file); // *** Do not make any other Scanners ***//

			// *** NOTE ***
			// For this assignment, you are NOT allowed to use any member methods of
			// class java.util.Scanner except hasNextLine and nextLine.
			// For example, you CANNOT use any of hasNextInt, nextInt, hasNextFloat,
			// nextFloat,
			// hasNextDouble and nextDouble for this assignment.


			// Process the input file using hasNextLine and nextLine
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				processLine(line);
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR - cannot open i?.txt \n");
		}
	}

	// lexical analysis of a single line
	private static void processLine(String line) {
		// Initialize character index
		int currentIndex = 0;

		// Iterate over characters in the line as long as current index is less than
		// length
		while (currentIndex < line.length()) {
			char currentChar = line.charAt(currentIndex);
			StringBuilder currentLexeme = new StringBuilder();

			// Skip whitespace characters
			if (Character.isWhitespace(currentChar)) {
				currentIndex++;
				continue;
			}

			// Handle letters and identifiers
			if (Character.isLetter(currentChar)) {
				if (currentIndex < line.length() &&
						(Character.isLetterOrDigit(line.charAt(currentIndex)) || line.charAt(currentIndex) == '_')) {
					currentLexeme.append(line.charAt(currentIndex));
					currentIndex++;
				}
				String lexemeStr = currentLexeme.toString().toLowerCase();
				if (lexemeStr.equals("f")) // f
					printToken(FLOATDCL, lexemeStr);
				else if (lexemeStr.equals("i")) // i
					printToken(INTDCL, lexemeStr);
				else if (lexemeStr.equals("p")) // p
					printToken(PRINT, lexemeStr);
				else {
					for (int i = 0; i < lexemeStr.length(); i++) { // Other 23 letters:
																	// a+b+c+d+e+g+h+j+k+l+m+n+o+q+r+s+t+u+v+w+x+y+z
						printToken(ID, String.valueOf(lexemeStr.charAt(i)));
					}
				}
			}
			// Dealing with digits and decimals
			else if (Character.isDigit(currentChar)) {
				boolean isFloatingPoint = false;

				while (currentIndex < line.length() &&
						(Character.isDigit(line.charAt(currentIndex)) || line.charAt(currentIndex) == '.')) {
					char digitChar = line.charAt(currentIndex);
					if (digitChar == '.') {
						if (isFloatingPoint) {
							break; // gets out of loop once consecutive decimal points is encountered
						}
						isFloatingPoint = true;
					}
					currentLexeme.append(digitChar);
					currentIndex++;
				}

				if (isFloatingPoint) { // (0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9)^+.(0 + 1 + 2
					printToken(FNUM, currentLexeme.toString());
				} else { // (0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9)^+
					printToken(INUM, currentLexeme.toString());
				}
			}
			// Dealing with operators
			else {
				switch (currentChar) {
					case '=':
						currentLexeme.append(currentChar);
						currentIndex++;
						printToken(ASSIGN, currentLexeme.toString());
						break;
					case '+':
						currentLexeme.append(currentChar);
						currentIndex++;
						printToken(PLUS, currentLexeme.toString());
						break;
					case '-':
						currentLexeme.append(currentChar);
						currentIndex++;
						printToken(MINUS, currentLexeme.toString());
						break;
					default:
						// System.out.println("Error - Undefined or invalid symbol: " + currentChar);
						// for testing purposes
						System.exit(1); // Exit program when invalid lexeme is found
				}
			}
		}
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
}
