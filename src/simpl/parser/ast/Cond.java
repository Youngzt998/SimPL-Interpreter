package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public Cond replace(Symbol x, Expr e) {
        return new Cond(e1.replace(x, e), e2.replace(x, e), e3.replace(x, e));
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        /**
         *
         */
        TypeResult tr1 = e1.typecheck(E);
        TypeEnv NewE1 = tr1.s.compose(E);
        TypeResult tr2 = e2.typecheck(NewE1);
        TypeEnv NewE2 = tr2.s.compose(NewE1);
        TypeResult tr3 = e3.typecheck(NewE2);
        Substitution comp =tr3.s.compose(tr2.s.compose(tr1.s));

        /**
         *      Rule CT-COND:
         *      G |- e1: t1, q1    G |- e2: t2, q2    G |- e3: t3, q3
         *      -----------------------------------------------------
         *      G |- if e1 then e2 else e3: t3, q1 U q2 U q3 U {t1 = bool} U {t2 = t3}
         */
        Substitution s1 = comp.apply(tr1.t).unify(Type.BOOL);
        comp = comp.compose(s1);
        Substitution s2 = comp.apply(tr2.t).unify(comp.apply(tr3.t));
        comp = comp.compose(s2);

        return TypeResult.of(comp, comp.apply(tr3.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue v1 = (BoolValue) e1.eval(s);
        if(v1.b)
            return e2.eval(s);
        else
            return e3.eval(s);
    }
}
