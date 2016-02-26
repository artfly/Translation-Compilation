import org.junit.Test;
import java.io.StringReader;
import static org.junit.Assert.*;

public class ParserTest {
    private Parser parser;

    @Test
    public void testParseAtom() {
        String num = String.valueOf(12345);
        parser = new Parser(new StringReader(num));
        assertEquals(Integer.parseInt(num), parser.parseAtom());
        parser = new Parser(new StringReader("(" + num + ")"));
        assertEquals(Integer.parseInt(num), parser.parseAtom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseAtomException() {
        parser = new Parser(new StringReader("IllegalArgumentException"));
        parser.parseAtom();
    }

    @Test
    public void testParsePower() {
        String num = String.valueOf(-12345);
        parser = new Parser(new StringReader(num));
        assertEquals(Integer.parseInt(num), parser.parsePower());
    }

    @Test
    public void testParseFactor() {
        String factor = String.valueOf(23);
        String power = String.valueOf(4);
        parser = new Parser(new StringReader(factor + " ^ " + power));
        assertEquals((int)Math.pow(Integer.parseInt(factor), Integer.parseInt(power)), parser.parseFactor());
    }

    @Test
    public void testParseTerm() {
        String first = String.valueOf(12);
        String second = String.valueOf(34);
        parser = new Parser(new StringReader(first + " * " + second));
        assertEquals(Integer.parseInt(first) * Integer.parseInt(second), parser.parseTerm());
        parser = new Parser(new StringReader(first + " / " + second));
        assertEquals(Integer.parseInt(first) / Integer.parseInt(second), parser.parseTerm());
    }

    @Test
    public void testParseExpr() {
        String first = String.valueOf(12);
        String second = String.valueOf(34);
        String third = String.valueOf(5);
        parser = new Parser(new StringReader(first + " + " + second + "-" + third));
        assertEquals(Integer.parseInt(first) + Integer.parseInt(second) - Integer.parseInt(third), parser.parseExpr());
        parser = new Parser(new StringReader(first + " - " + second));
        assertEquals(Integer.parseInt(first) - Integer.parseInt(second), parser.parseExpr());
    }
}
