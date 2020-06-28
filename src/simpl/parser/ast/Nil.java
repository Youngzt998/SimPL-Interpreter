package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Nil extends Expr {

    public String toString() {
        return "nil";
    }


    public Nil replace(Symbol x, Expr e) {
        return this;
    }


    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        /**
         *  Introduce a new type variable
         */
        TypeVar tv = new TypeVar(true);
        return TypeResult.of(new ListType(tv));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return Value.NIL;
    }
}
