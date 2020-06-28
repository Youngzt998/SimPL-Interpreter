package simpl.parser.ast;

import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Pair extends BinaryExpr {

    public Pair(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(pair " + l + " " + r + ")";
    }

    public Pair replace(Symbol x, Expr e) {
        return new Pair(l.replace(x, e), r.replace(x, e));
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        /**
         *
         */
        TypeResult tr1 = l.typecheck(E);
        TypeEnv NewE = tr1.s.compose(E);
        TypeResult tr2 = r.typecheck(NewE);

        /**
         *      Rule CT-PAIR:
         *      ...
         */
        Substitution comp = tr2.s.compose(tr1.s);

        return TypeResult.of(comp, new PairType(comp.apply(tr1.t), comp.apply(tr2.t)));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *  Evaluate the first, then the second, then pair up them
         *  Q: How to transport the memory change to the second one?
         *      e.g.  ( e1:= v1, if (uop e1) then e2 else e3 )
         *
         *  A: l.eval(s) will probably change s w.r.t. l's memory operation,
         *        so we do not worry about the memory changes in
         */
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return new PairValue(v1, v2);
    }
}
