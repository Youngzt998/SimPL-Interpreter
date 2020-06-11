package simpl.interpreter.pcf;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class iszero extends FunValue {

    public iszero() {
        // TODO
        super(Env.empty, Symbol.symbol("iszero"), getExpr());
    }

    private static Expr getExpr(){
        Expr e = new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                IntValue v =(IntValue) s.E.get(Symbol.symbol("iszero"));
                return v.n == 0? new BoolValue(true) : new BoolValue(false);
            }
        };
        return e;
    }
}
