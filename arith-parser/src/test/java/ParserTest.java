import org.junit.Test;

import java.io.StringReader;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.Assert.*;

/**
 * Created by arty on 10.02.16.
 */
public class ParserTest {
    private Parser parser;

    @Test
    public void testParseAtom() {
        String num = String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        parser = new Parser(new StringReader(num));
        assertEquals(Integer.parseInt(num), parser.parseAtom());
        String expr = String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        parser = new Parser(new StringReader("(" + expr + ")"));
        assertEquals(Integer.parseInt(expr), parser.parseAtom());
    }

    @Test
    public void testParsePower() {
        String num = String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, 0));
        parser = new Parser(new StringReader(num));
        assertEquals(Integer.parseInt(num), parser.parsePower());
    }

    @Test
    public void testParseFactor() {
        String factor = String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        String power = String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        parser = new Parser(new StringReader(factor + " ^ " + power));
        assertEquals((int)Math.pow(Integer.parseInt(factor), Integer.parseInt(power)), parser.parseFactor());
    }

    @Test
    public void testParseTerm() {
        String first = String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        String second = String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        parser = new Parser(new StringReader(first + " * " + second));
        assertEquals(Integer.parseInt(first) * Integer.parseInt(second), parser.parseTerm());
        parser = new Parser(new StringReader(first + " / " + second));
        assertEquals(Integer.parseInt(first) / Integer.parseInt(second), parser.parseTerm());
    }

    @Test
    public void testParseExpr() {
        String first = String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        String second = String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        String third = String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        parser = new Parser(new StringReader(first + " + " + second + "-" + third));
        assertEquals(Integer.parseInt(first) + Integer.parseInt(second) - Integer.parseInt(third), parser.parseExpr());
        parser = new Parser(new StringReader(first + " - " + second));
        assertEquals(Integer.parseInt(first) - Integer.parseInt(second), parser.parseExpr());
    }
}
