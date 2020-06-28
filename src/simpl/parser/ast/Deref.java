package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }


    @Override
    public Deref replace(Symbol x, Expr e) {
        return new Deref(this.e.replace(x, e));
    }


    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr = e.typecheck(E);

        /**
         *      Rule CT-DEREF:
         *      G |- e: t, q
         *      ----------------------
         *      G |- !e: a, q U {t = ref a}
         */
        RefType rt = new RefType(new TypeVar(true));
        Substitution s = tr.t.unify(rt);
        s = tr.s.compose(s);

        return TypeResult.of(s, s.apply(rt.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *  e -> v where v = (ref vp)
         *  !e -> M(vp) where s = (E, M, p)
         */
        RefValue v = (RefValue) e.eval(s);
        return s.M.get(v.p);
    }
}
