package simpl.parser.ast;

import simpl.typing.*;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        /**
         *
         */
        TypeResult tr1 = l.typecheck(E);
        if(!tr1.t.isEqualityType())
            throw new TypeMismatchError();

        TypeEnv NewE = tr1.s.compose(E);
        TypeResult tr2 = r.typecheck(NewE);

        if(!tr2.t.isEqualityType() )
            throw new TypeMismatchError();

        /**
         *      Rule CT-EQ:
         *      G |- e1: t1, q1    G |- e2: t2, q2
         *      -----------------------------------------
         *      G |- e1 Eq e2: bool, q1 U q2 U {t1 = t2}
         */
        Substitution comp = tr2.s.compose(tr1.s);
        Substitution s =comp.apply(tr1.t).unify(comp.apply(tr2.t));
        comp = comp.compose(s);

        return TypeResult.of(comp, Type.BOOL);
    }
}
