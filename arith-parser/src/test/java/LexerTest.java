import com.sun.deploy.util.StringUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.StringReader;
import java.util.*;

public class LexerTest {
    @Test
    public void testLexer() {
        final Map<String, Lexeme.LexemeTypes> lexemes = new HashMap<>();
        lexemes.put("-", Lexeme.LexemeTypes.MINUS);
        lexemes.put("+", Lexeme.LexemeTypes.PLUS);
        lexemes.put("*", Lexeme.LexemeTypes.MULT);
        lexemes.put("/", Lexeme.LexemeTypes.DIV);
        lexemes.put("^", Lexeme.LexemeTypes.POWER);
        lexemes.put("(", Lexeme.LexemeTypes.OP_BRACKET);
        lexemes.put(")", Lexeme.LexemeTypes.CL_BRACKET);
        lexemes.put("123", Lexeme.LexemeTypes.NUM);
        StringReader reader = new StringReader(StringUtils.join(lexemes.keySet(), " "));

        Lexer lexer = new Lexer(reader);
        Lexeme lexeme;
        for (Map.Entry<String, Lexeme.LexemeTypes> entry : lexemes.entrySet()) {
            lexeme = lexer.getNextLexeme();
            assertEquals(entry.getKey(), lexeme.lexeme);
            assertEquals(entry.getValue(), lexeme.type);
        }
        assertEquals(Lexeme.LexemeTypes.EOF, lexer.getNextLexeme().type);
    }
}
