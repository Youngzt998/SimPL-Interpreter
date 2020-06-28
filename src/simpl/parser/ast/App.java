package simpl.parser.ast;

import com.sun.xml.internal.bind.v2.TODO;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }


    @Override
    public App replace(Symbol x, Expr e) {
        return new App(l.replace(x, e), r.replace(x, e));
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
         *      We get: [G |- l: T1, q1] and [G |- r: T2, q2]
         *
         *      Rules CT-APP
         *      G |- l: t1, q1    G |- r: t2, q2
         *      --------------------------------------
         *      G |- l r : a, q1 U q2 U {t1 = t2 -> a}
         */
        Substitution comp = tr2.s.compose(tr1.s);
        ArrowType arrow = new ArrowType(comp.apply(tr2.t), new TypeVar(true));
        Substitution s = comp.apply(tr1.t).unify(arrow);
        comp = comp.compose(s);

        return TypeResult.of(comp, comp.apply(arrow.t2));   //TODO

    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *  l r with (E, M, p)
         *      ->  (Fn x => e) r with (E, M, p)
         */

        /**
         * First:  l -> v1, where v1 = (Fn x => e), or (fun, E1, x, e) as a FunValue
         */
        FunValue v1 = (FunValue) l.eval(s);

        /**
         * Second: r -> v2
         */
        Value v2 = r.eval(s);

        /**  Third:  v1 v2 (apply v2 to v1)
         *      (Fn x => e) v2
         *      -> e[v2/x]
         *   evaluate e, where x is replaced with v2
         *
         *   Update from E to E[x: v2] achieve this
         *      i.e. new Env(v1.E, v1.x, v2).
         */
        return v1.e.eval(State.of(new Env(v1.E, v1.x, v2), s.M, s.p));

    }
}
