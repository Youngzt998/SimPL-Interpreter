package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Neq extends EqExpr {

    public Neq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " <> " + r + ")";
    }


    public Neq replace(Symbol x, Expr e) {
        return new Neq(l.replace(x, e), r.replace(x, e));
    }


    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new BoolValue(!l.eval(s).equals(r.eval(s)));
    }
}
