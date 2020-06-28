package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class fst extends FunValue {

    public fst() {
        // TODO
        /**
         *  TODO
         */

        super(Env.empty, Symbol.symbol("fst"), getExpr());
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
                PairValue v =(PairValue) s.E.get(Symbol.symbol("fst"));
                return v.v1;
            }
        };
        return e;
    }

}
