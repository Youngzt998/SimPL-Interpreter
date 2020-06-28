package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }


    @Override
    public Cons replace(Symbol x, Expr e) {
        return new Cons(l.replace(x, e), r.replace(x, e));
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        /**
         *  Same as ArithExpr.java
         */
        TypeResult tr1 = l.typecheck(E);
        TypeEnv NewE = tr1.s.compose(E);
        TypeResult tr2 = r.typecheck(NewE);

        /**
         *      Rule CT-CONS:
         *      G |- e1: t1, q1    G |- e2: t2, q2
         *      ------------------------------------------------------------
         *      G |- cons e2 e2: list a, q1 U q2 U {t2 = list a} U {t1 = a}
         */
        Substitution comp = tr2.s.compose(tr1.s);
        TypeVar a = new TypeVar(true);
        ListType lt = new ListType(a);
        Substitution s = comp.apply(tr2.t).unify(comp.apply(lt));
        comp = comp.compose(s);
        s = comp.apply(tr1.t).unify(comp.apply(a));
        comp = comp.compose(s);

        return TypeResult.of(comp, comp.apply(tr2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *  First: evaluate the head
         *  Second: evaluate the tail
         */
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return new ConsValue(v1, v2);
    }
}
