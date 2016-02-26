import org.junit.Test;
import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by arty on 10.02.16.
 */
public class LexerTest {
    private StringReader reader;
    @Test
    public void testLexer() {
        List<String> lexemes = Arrays.asList("-", "+",  "*", "/", "^", "(", ")", "123");
        String expr = String.join(" ", lexemes);
        reader = new StringReader(expr);

        Lexer lexer = new Lexer(reader);
        for (String expected : lexemes) {
            assertEquals(expected, lexer.getNextLexeme().lexeme);
        }
        assertEquals(Lexeme.LexemeTypes.EOF, lexer.getNextLexeme().type);
    }
}
