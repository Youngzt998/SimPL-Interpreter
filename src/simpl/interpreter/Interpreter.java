package simpl.interpreter;

import java.io.FileInputStream;
import java.io.InputStream;

import simpl.parser.Parser;
import simpl.parser.Symbol;
import simpl.parser.SyntaxError;
import simpl.parser.ast.Expr;
import simpl.parser.ast.Nil;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.TypeError;

public class Interpreter {

    public void run(String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser parser;
            java_cup.runtime.Symbol parseTree;
            Expr program;
            try {
                parser = new Parser(inp);
                parseTree = parser.parse();
                program = (Expr) parseTree.value;
            }catch (Exception e){
                System.out.println("syntax error");
                return;
            }
            System.out.println(program.typecheck(new DefaultTypeEnv()).t);
            try{
                System.out.println(program.eval(new InitialState()));
            }catch (StackOverflowError e){
                throw new RuntimeError("runtime error: system memory not enough");
            }

        }
        catch (SyntaxError e) {
            System.out.println("syntax error");
        }
        catch (TypeError e) {
            System.out.println("type error");
        }
        catch (RuntimeError e) {
            System.out.println("runtime error");
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void interpret(String filename) {
        Interpreter i = new Interpreter();
        System.out.println(filename);
        i.run(filename);
    }

    public static void main(String[] args) {
        interpret(args[0]);
//        interpret("doc/test/test_add.spl");
//        interpret("doc/examples/plus.spl");
//        interpret("doc/examples/true.spl");
//        interpret("doc/examples/factorial.spl");
//        interpret("doc/examples/gcd1.spl");
//        interpret("doc/examples/gcd2.spl");
//        interpret("doc/examples/max.spl");
//        interpret("doc/examples/sum.spl");
//        interpret("doc/examples/map.spl");
//        interpret("doc/examples/pcf.sum.spl");
//        interpret("doc/examples/pcf.even.spl");
//        interpret("doc/examples/pcf.minus.spl");
//        interpret("doc/examples/pcf.factorial.spl");
//        interpret("doc/examples/pcf.fibonacci.spl");
//        interpret("doc/examples/pcf.twice.spl");
//        interpret("doc/examples/pcf.lists.spl");
//        interpret("doc/examples/letpoly.spl");

    }
}
