package simpl.parser.ast;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
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
        /**
            Type check l under E ==> tr1 = {t: the type of l, s: substitution}
            Type check r under a new Type Environment E1 = tr1.s.compose(E),
                where E1(x) = tr1.s(E(x))
                ==> tr2 = {t: the type of r, s: substitution}
            We need to check r under a new environment because some variable might
                be inferred its type when checking l [e.g. (x + 1) + (x + 2)]
        */
        TypeResult tr1 = l.typecheck(E);
        TypeEnv NewE = tr1.s.compose(E);
        TypeResult tr2 = r.typecheck(NewE);

        /**
         *  Compose two substitution to get a new substitution
         *      then compose two substitution that {tr1.t = Int} and {tr2.t = Int}
         *
         *      G |- e1: t1, q1    G |- e2:t2, q2    bop \in {+, -, *, /}
         *      ------------------------------------------------------------
         *      G |- e1 bop e2: int, q1 U q2 U {q1(q2(t1)) = int} U {q1(q2(q3(t2))) = int}
         */
        Substitution comp = tr1.s.compose(tr2.s);
        Substitution s1 = comp.apply(tr1.t).unify(Type.INT);
        comp = comp.compose(s1);
        Substitution s2 = comp.apply(tr2.t).unify(Type.INT);
        comp = comp.compose(s2);


        /**
         *  To do: ... ?
         */
        return TypeResult.of(comp, Type.INT);
    }
}
