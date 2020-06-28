package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Sub extends ArithExpr {

    public Sub(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " - " + r + ")";
    }

    public Sub replace(Symbol x, Expr e) {
        return new Sub(l.replace(x, e), r.replace(x, e));
    }


    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /*
            Check Type ?
            Or type already checked here?
        */
        return new IntValue(((IntValue)l.eval(s)).n - ((IntValue)r.eval(s)).n);
    }
}
