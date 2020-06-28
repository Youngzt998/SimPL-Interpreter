package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;

public class Add extends ArithExpr {

    public Add(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " + " + r + ")";
    }

    @Override
    public Add replace(Symbol x, Expr e) {
        return new Add(l.replace(x, e), r.replace(x, e));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /*
            Check Type ?
            Or type already checked here?
        */
        return new IntValue(((IntValue)l.eval(s)).n + ((IntValue)r.eval(s)).n);
    }
}
