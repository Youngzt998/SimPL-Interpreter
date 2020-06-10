package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Div extends ArithExpr {

    public Div(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " / " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /*
            Check Type ?
            Or type already checked here?
        */
        return new IntValue(((IntValue)l.eval(s)).n / ((IntValue)r.eval(s)).n);
    }
}
