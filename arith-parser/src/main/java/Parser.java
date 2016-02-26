import java.io.Reader;

public class Parser {
    private Lexer lexer;
    private Lexeme lexeme;

    Parser(Reader reader) {
        lexer = new Lexer(reader);
        lexeme = lexer.getNextLexeme();
    }

    public int parseExpr() {
        int result = parseTerm();
        while (lexeme.type == Lexeme.LexemeTypes.PLUS || lexeme.type == Lexeme.LexemeTypes.MINUS) {
            if (lexeme.type == Lexeme.LexemeTypes.PLUS) {
                lexeme = lexer.getNextLexeme();
                result += parseTerm();
            }
            if (lexeme.type == Lexeme.LexemeTypes.MINUS) {
                lexeme = lexer.getNextLexeme();
                result -= parseTerm();
            }
        }
        lexeme = lexer.getNextLexeme();
        return result;
    }

    public int parseTerm() {
        int result = parseFactor();
        while (lexeme.type == Lexeme.LexemeTypes.MULT || lexeme.type == Lexeme.LexemeTypes.DIV) {
            if (lexeme.type == Lexeme.LexemeTypes.MULT) {
                lexeme = lexer.getNextLexeme();
                result *= parseFactor();
            }
            if (lexeme.type == Lexeme.LexemeTypes.DIV) {
                lexeme = lexer.getNextLexeme();
                result /= parseFactor();
            }
        }
        return result;
    }

    public int parseFactor() {
        int result = parsePower();
        if (lexeme.type == Lexeme.LexemeTypes.POWER) {
            lexeme = lexer.getNextLexeme();
            result = (int) Math.pow(result, parseFactor());
        }
        return result;
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
        if (lexeme.type == Lexeme.LexemeTypes.OP_BRACKET) {
            lexeme = lexer.getNextLexeme();
            return parseExpr();
        }
        throw new IllegalArgumentException();
    }

}
