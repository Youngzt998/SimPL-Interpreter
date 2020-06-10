package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO

        /*
            tr1: (t1, q1)
            tr2: (t2, q2)
            Target result:
        */
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        // compose two substitution in two type result
        Substitution comp = tr1.s.compose(tr2.s);



        return null;
    }
}
