package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class RelExpr extends BinaryExpr {

    public RelExpr(Expr l, Expr r) {
        super(l, r);
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
         *      Rule CT-REL:
         *      G |- e1: t1, q1    G |- e2: t2, q2
         *      -----------------------------------------
         *      G |- e1 Rel e2: bool, q1 U q2 U {t1 = int} U {t2 = int}
         */
        Substitution comp = tr2.s.compose(tr1.s);
        Substitution s1 = comp.apply(tr1.t).unify(Type.INT);
        comp = comp.compose(s1);
        Substitution s2 = comp.apply(tr2.t).unify(Type.INT);
        comp = comp.compose(s2);

        return TypeResult.of(comp, Type.BOOL);

    }
}
