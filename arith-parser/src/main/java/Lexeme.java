/**
 * Created by arty on 09.02.16.
 */
public class Lexeme {
    public String lexeme;
    public LexemeTypes type;
    public enum LexemeTypes {
        OP_BRACKET, NUM, CL_BRACKET, PLUS, MINUS, POWER, MULT, DIV, EOF
    }
    public Lexeme(String lexeme, LexemeTypes type) {
        this.type = type;
        this.lexeme = lexeme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lexeme lexeme1 = (Lexeme) o;

        if (lexeme != null ? !lexeme.equals(lexeme1.lexeme) : lexeme1.lexeme != null) return false;
        return type == lexeme1.type;

    }

    @Override
    public int hashCode() {
        int result = lexeme != null ? lexeme.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
