import java.io.*;

/**
 * Created by arty on 09.02.16.
 */
public class Main {
    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.err.println("Usage : arith-parser <path/to/file>");
//            System.exit(1);
//        }
//        Reader reader = null;
//        try {
//            reader = new FileReader(args[0]);
//            System.out.println(new Parser(reader));
//        } catch (FileNotFoundException e) {
//            System.out.println("Error : no such file");
//        } finally {
//            if (reader != null)
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                }
//        }
        Reader reader = new StringReader("3 * 10 / 11");
        System.out.println((new Parser(reader)).parseExpr());
    }
}