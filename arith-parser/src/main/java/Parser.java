import java.io.Reader;

/**
 * Created by arty on 10.02.16.
 */
public class Parser {
    private Lexer lexer;
    private Lexeme lexeme;

    Parser(Reader reader) {
        lexer = new Lexer(reader);
        lexeme = lexer.getNextLexeme();
    }

    public int parseExpr() {
        int result;
        result = parseTerm();
        if (lexeme.type == Lexeme.LexemeTypes.PLUS) {
            lexeme = lexer.getNextLexeme();
            return result + parseExpr();
        }
        if (lexeme.type == Lexeme.LexemeTypes.MINUS) {
            lexeme = lexer.getNextLexeme();
            return result - parseExpr();
        }
        if (lexeme.type == Lexeme.LexemeTypes.CL_BRACKET) {
            lexeme = lexer.getNextLexeme();
            System.out.println("returns " + result);
            return result;
        }
//        lexeme = lexer.getNextLexeme();
        return result;
    }

    public int parseTerm() {
        int result = parseFactor();
        if (lexeme.type == Lexeme.LexemeTypes.MULT) {
            lexeme = lexer.getNextLexeme();
            System.out.println("mult " + result);
            result *= parseTerm();
            System.out.println("return " + result);
            return result;
        }
        if (lexeme.type == Lexeme.LexemeTypes.DIV) {
            lexeme = lexer.getNextLexeme();
            return result / parseTerm();
        }
        return result;
    }

    public int parseFactor() {
        int result = parsePower();
        if (lexeme.type != Lexeme.LexemeTypes.POWER)
           return result;
        lexeme = lexer.getNextLexeme();
        return (int)Math.pow(result, parseFactor());
    }

    public int parsePower() {
        if (lexeme.type == Lexeme.LexemeTypes.MINUS) {
            lexeme = lexer.getNextLexeme();
            return -1 * parseAtom();
        }
        return parseAtom();
    }

    public int parseAtom() {
        if (lexeme.type == Lexeme.LexemeTypes.NUM) {
            int result = Integer.parseInt(lexeme.lexeme);
            lexeme = lexer.getNextLexeme();
            return result;
        }
        else if (lexeme.type == Lexeme.LexemeTypes.OP_BRACKET || lexeme.type == Lexeme.LexemeTypes.EOF) {
            lexeme = lexer.getNextLexeme();
            return parseExpr();
        }
        else {
            throw new IllegalArgumentException();
        }
    }

}
