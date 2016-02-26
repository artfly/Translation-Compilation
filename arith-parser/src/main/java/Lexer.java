import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by arty on 09.02.16.
 */
public class Lexer {
    private Reader reader;
    private StringBuilder builder;
    private String lexeme;

    Lexer(Reader reader) {
        this.reader = reader;
        builder = new StringBuilder();
    }

    public Lexeme getNextLexeme() {
        int i;
        if ((builder.length() == 1) && (!Character.isDigit(builder.charAt(0))) && (!Character.isSpaceChar(builder.charAt(0)))) {
            char c = builder.toString().charAt(0);
            builder.setLength(0);
            switch (c) {
                case '+' :
                    System.out.println(Character.toString(c));
                    return new Lexeme(Character.toString(c), Lexeme.LexemeTypes.PLUS);
                case '-' :
                    System.out.println(Character.toString(c));
                    return new Lexeme(Character.toString(c), Lexeme.LexemeTypes.MINUS);
                case '*' :
                    System.out.println(Character.toString(c));
                    return new Lexeme(Character.toString(c), Lexeme.LexemeTypes.MULT);
                case '/' :
                    System.out.println(Character.toString(c));
                    return new Lexeme(Character.toString(c), Lexeme.LexemeTypes.DIV);
                case '^':
                    System.out.println(Character.toString(c));
                    return new Lexeme(Character.toString(c), Lexeme.LexemeTypes.POWER);
                case '(':
                    System.out.println(Character.toString(c));
                    return new Lexeme(Character.toString(c), Lexeme.LexemeTypes.OP_BRACKET);
                case ')':
                    System.out.println(Character.toString(c));
                    return new Lexeme(Character.toString(c), Lexeme.LexemeTypes.CL_BRACKET);
                default:

                    return new Lexeme(Character.toString(c), Lexeme.LexemeTypes.EOF);
            }
        }

        try {
             do {
                 i = reader.read();
                 if (Character.isDigit(i)) {
                    builder.append((char)i);
                 }
                 else if (!Character.isSpaceChar(i)) {
                    if (builder.length() > 0) {
                        lexeme = builder.toString();
                        builder.setLength(0);
                        builder.append((char) i);
                        System.out.println(lexeme);
                        return new Lexeme(lexeme, Lexeme.LexemeTypes.NUM);
                    }
                    else {
                        builder.append((char) i);
                        return getNextLexeme();
                    }
                 }
             }
             while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
