package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class GreaterEq extends RelExpr {

    public GreaterEq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " >= " + r + ")";
    }


    @Override
    public GreaterEq replace(Symbol x, Expr e) {
        return new GreaterEq(l.replace(x, e), r.replace(x, e));
    }


    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new BoolValue(((IntValue)l.eval(s)).n >= ((IntValue)r.eval(s)).n);
    }
}
