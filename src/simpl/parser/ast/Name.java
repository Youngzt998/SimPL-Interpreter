package simpl.parser.ast;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }


    public Expr replace(Symbol x, Expr e) {
        if(this.x.toString().equals(x.toString()))
            return e;
        else return this;

    }


    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        if (E.get(x) == null)
            throw new TypeError("type error: symbol " + x + "not found");

        return TypeResult.of(E.get(x));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *  If x is bounded to normal value, just get the value;                      [E-Name1]
         *      but if x is bounded to v = (rec, E', x, e) w.r.t. (E, M, p)           [E-Name2]
         *      we cannot just do this since (v a) cannot evaluate further,
         *      instead, we go on evaluating rec x => e w.r.t (E', M, p).
         */
        Value v = s.E.get(x);
        if (v==null)
            throw new RuntimeError("runtime error");
        else if (v instanceof RecValue){
            return new Rec(((RecValue) v).x, ((RecValue) v).e).eval(State.of(((RecValue) v).E, s.M, s.p));
        }else {
            return v;
        }
    }
}
