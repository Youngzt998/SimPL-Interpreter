package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }


    @Override
    public Assign replace(Symbol x, Expr e) {
        return new Assign(l.replace(x, e), r.replace(x, e));
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
         *      Rule CT-ASSIGN:
         *      G |- e1: t1, q1    G |- e2: t2, q2
         *      --------------------------------------------------------
         *      G |- e1 := e2 : unit, q1 U q2 U {t1 = ref t2}
         */
        Substitution comp = tr2.s.compose(tr1.s);

        RefType rt = new RefType(tr2.t);
        Substitution s = comp.apply(tr1.t).unify(comp.apply(rt));
        comp = comp.compose(s);

        return TypeResult.of(comp, Type.UNIT);
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
