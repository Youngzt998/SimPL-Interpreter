package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *  Fist:   l -> v1
         *      where v1 = ref p
         *  Second: r -> v2
         *      where v2 = ...  can be any value?
         *  Third:
         *      update memory
         */

        RefValue v1 = (RefValue)l.eval(s);
        Value v2 = r.eval(s);
        s.M.put(v1.p, v2);
        return Value.UNIT;
    }
}
