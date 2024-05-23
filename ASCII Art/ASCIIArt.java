public class ASCIIArt {
    // Do NOT change the following line except the SIZE value.
    // Do NOT reference SIZE in any field declaration.
    public static int SIZE = 2;

    public static void main(String[] args) {

        // Do NOT change the following two lines.
        if (args.length > 0 && args[0] != null)
            SIZE = Integer.parseInt(args[0]);

        // Call methods
        printHashTag(SIZE); // Print Top Hashtag Line
        printVerticalBars(SIZE); // Print Vertical Bar Lines
        printHashTag(SIZE); // Print Bottom Hastag Line

        printTilde(SIZE); // Print Top Tilde Line
        // Loop to print correct number of Tilde and HyphenO Lines after first Tilde
        for (int pairs = 1; pairs <= (SIZE * SIZE); pairs++) {
            // size = 2 3 4 5 6 7
            // # of pairs = 4 9 16 25 36 49
            // Multiplied by itself (squared) offset by 0

            printHyphenO(SIZE);
            printTilde(SIZE);
        }

        printQuoteLines(SIZE);
        printQuotesOs(SIZE);
    }
    // You are not allowed to use anything besides println/print statements,
    // static methods, method calls, loops, nested loops, local variables, and class
    // constants.

    // You are NOT allowed to use method parameters, methods that return values, or
    // conditional statements (i.e., if, if/else statements).

    public static void printHashTag(int size) {
        // Print dots before hashtags
        for (int dotsFirst = 1; dotsFirst <= (4 * size) + 2; dotsFirst++) {
            System.out.print(".");

            // size = 2 3 4 5 6 7
            // # of dots = 10 14 18 22 26 30
            // Multiply by 4 offset by +2
        }

        // Print hashtags
        for (int hashtag = 1; hashtag <= (2 * size) - 1; hashtag++) {
            System.out.print("#");

            // size = 2 3 4 5 6 7
            // # of Hashtags = 3 5 7 9 11 13
            // Multiply by 2 offset by -1
        }

        // Print dots after hashtags
        for (int dotsAfter = 1; dotsAfter <= (4 * size) + 2; dotsAfter++) {
            System.out.print(".");

            // size = 2 3 4 5 6 7
            // # of dots = 10 14 18 22 26 30
            // Multiply by 4 offset by +2
        }

        // Move to the next line
        System.out.println();
    }

    public static void printVerticalBars(int size) {
        for (int line = 1; line <= (size * 2) - 2; line++) {
            // size = 2 3 4 5 6 7
            // # of lines= 2 4 6 8 10 12
            // Multiply by 2 offset by -2

            // Print dots before vertical bars
            for (int dotsFirst = 1; dotsFirst <= (4 * size) + 2; dotsFirst++) {
                System.out.print(".");

                // size = 2 3 4 5 6 7
                // # of dots = 10 14 18 22 26 30
                // Multiply by 4 offset by +2
            }

            // Print vertical bars
            for (int hashtag = 1; hashtag <= (2 * size) - 1; hashtag++) {
                System.out.print("|");

                // size = 2 3 4 5 6 7
                // # of dots = 3 5 7 9 11 13
                // Multiply by 2 offset by -1
            }

            // Print dots after hashtags
            for (int dotsAfter = 1; dotsAfter <= (4 * size) + 2; dotsAfter++) {
                System.out.print(".");

                // size = 2 3 4 5 6 7
                // # of dots = 10 14 18 22 26 30
                // Multiply by 4 offset by +2
            }

            // Move to the next line
            System.out.println();

        } // end nested loop
    }// end method

    public static void printTilde(int size) {
        // Print dots before Tildes
        for (int dotsFirst = 1; dotsFirst <= 4 * size; dotsFirst++) {
            System.out.print(".");

            // size = 2 3 4 5 6 7
            // # of dots = 8 12 16 20 24 28
            // Multiply by 4 offset by 0
        }

        // Print Tildes
        for (int tildes = 1; tildes <= (2 * size) + 3; tildes++) {
            System.out.print("~");

            // size = 2 3 4 5 6 7
            // # of Tildes = 7 9 11 13 15 17
            // Multiply by 2 offset by +3
        }

        // Print dots after Tildes
        for (int dotsLast = 1; dotsLast <= 4 * size; dotsLast++) {
            System.out.print(".");

            // size = 2 3 4 5 6 7
            // # of dots = 8 12 16 20 24 28
            // Multiply by 4 offset by 0
        }
        // Move to the next line
        System.out.println();
    }// end method

    public static void printHyphenO(int size) {
        // Print dots before Vertical Bar
        for (int dotsFirst = 1; dotsFirst <= 4 * size; dotsFirst++) {
            System.out.print(".");

            // size = 2 3 4 5 6 7
            // # of dots = 8 12 16 20 24 28
            // Multiply by 4 offset by 0
        }

        // Print first Vertical Bar
        System.out.print("|");

        // Print Hypehens and Os but discluding last Hyphen
        for (int hypenAndO = 1; hypenAndO <= size; hypenAndO++) {
            System.out.print("-O");

            // size = 2 3 4 5 6 7
            // # of hyphenAndO = 2 3 4 5 6 7
            // Multiply by 1 offset by 0
        }

        // Print Last Hyphen
        System.out.print("-");

        // Print Last Vertical Bar
        System.out.print("|");

        // Print dots after Vertical Bar
        for (int dotsFirst = 1; dotsFirst <= 4 * size; dotsFirst++) {
            System.out.print(".");

            // size = 2 3 4 5 6 7
            // # of dots = 8 12 16 20 24 28
            // Multiply by 4 offset by 0
        }

        // Move to the next line
        System.out.println();

    }// end method

    public static void printQuoteLines(int size) {
        for (int line = 1; line <= (size / 2) + 1; line++) {
            // size = 2 3 4 5 6 7
            // #of lines = 2 2 3 3 4 4
            // divided by 2 +1

            // Print dots before /
            for (int dotsFirst = 1; dotsFirst <= (size / 2 + 1 - line) * 3; dotsFirst++) {
                System.out.print(".");
                // Quantity of (Divide size by 2 plus 1 - value of line) * by 3

                // When size = 2
                // first line = 3
                // second line = 0

                // when size = 3
                // first line = 3
                // second line = 0

                // when size = 4
                // first line = 6
                // second line = 3
                // third line = 0

            }

            // Print Starting /
            System.out.print("/");
            // Print Starter Double Quotes
            System.out.print("\"");

            // Print Pairs of '"
            for (int pairs = 1; pairs <= ((size * 3) + (size / 2)) + ((line - 1) * 3)
                    + (((size % 2) * ((size - 1) - (size % 3) - ((size / 6) * 3)))); pairs++) { // mod division will
                                                                                                // only
                                                                                                // affect
                // odd number
                // sizes where we//(size - 1) - (size % 3) - (size % 6))
                // need
                System.out.print("\'\"");

                // When size = 2
                // First Line = 7 pairs of '"
                // Second Line = 10 pairs of '"

                // When size = 3
                // First Line = 12 pairs of '" 3 more (size -1)
                // Second Line = 15 pairs of '"

                // When size = 4
                // First Line = 14 pairs of '"
                // Second Line = 17 pairs of '"
                // Third line = 20 pairs of '"

                // When size = 5
                // First Line = 19 pairs of '" 7 more than size = 3 (size -1) + (size % )
                // Second Line = 22 pairs of '"
                // Third line = 25 pairs of '"

                // When size = 6
                // First Line = 21 pairs of '"
                // Second Line = 24 pairs of '"
                // Third line = 27 pairs of '"
                // Fourth line = 30 pairs of '"

                // When size = 7
                // First Line = 26 pairs of '" 9 more than size = 5
                // Second Line = 29 pairs of '"
                // Third line = 32 pairs of '"
                // Fourth line = 35 pairs of '"
            }

            // Print End /
            System.out.print("\\");

            // Print dots after \
            for (int dotsFirst = 1; dotsFirst <= (size / 2 + 1 - line) * 3; dotsFirst++) {
                System.out.print(".");
            }

            // Skip Line
            System.out.println();
        } // end for loop
    }// end method

    public static void printQuotesOs(int size) {
        for (int line = 1; line <= size; line++) {
            // Print Starting /
            System.out.print("/");

            // Print Starter Double Quotes
            System.out.print("\"");

            // Print pairs of O"
            for (int pairs = 1; pairs <= size * 5; pairs++) {
                System.out.print("O\"");
                // Size = 2 3 4 5 6 7
                // # of pairs = 10 15 20 25 30 35
            }

            // Print End /
            System.out.print("\\");

            // Skip Line
            System.out.println();
        } // end for loop
    }// end method
}
