package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Seq extends BinaryExpr {

    public Seq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " ; " + r + ")";
    }

    public Seq replace(Symbol x, Expr e) {
        return new Seq(l.replace(x, e), r.replace(x, e));
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
         *      ----------------------------------
         *      G |- e1;e2: t2, q1 U q2
         */
        Substitution comp = tr2.s.compose(tr1.s);

        return TypeResult.of(comp, comp.apply(tr2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *  Evaluate l, then evaluate r
         *  Note: l.eval(s) will probably change s w.r.t. l's memory operation,
         *          so we do not worry about the memory changes in l
         */
        l.eval(s);
        return r.eval(s);
    }
}
