package simpl.interpreter.lib;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.parser.ast.Cons;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class hd extends FunValue {

    public hd() {
        // TODO
        super(Env.empty, Symbol.symbol("hd"), getExpr());
    }

    private static Expr getExpr(){
        Expr e = new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                try{
                    ConsValue v =(ConsValue) s.E.get(Symbol.symbol("hd"));
                    return v.v1;
                } catch (Exception e){
                    throw new RuntimeError("runtime error");
                }
            }
        };
        return e;
    }
}
