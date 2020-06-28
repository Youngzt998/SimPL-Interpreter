package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class tl extends FunValue {

    public tl() {
        // TODO
        super(Env.empty, Symbol.symbol("tl"), getExpr());
    }

    private static Expr getExpr(){
        Expr e = new Expr() {
            @Override
            public Expr replace(Symbol x, Expr e) {
                return this;
            }

            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return TypeResult.of(new TypeVar(true));
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                try{
                    ConsValue v =(ConsValue) s.E.get(Symbol.symbol("tl"));
                    return v.v2;
                } catch (Exception e){
                    throw new RuntimeError("runtime error");
                }
            }
        };
        return e;
    }
}
