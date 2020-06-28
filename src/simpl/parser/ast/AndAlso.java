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

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public AndAlso replace(Symbol x, Expr e) {
        return new AndAlso(l.replace(x, e), r.replace(x, e));
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
         *
         */
        Substitution comp = tr1.s.compose(tr2.s);
        Substitution s1 = comp.apply(tr1.t).unify(Type.BOOL);
        comp = comp.compose(s1);
        Substitution s2 = comp.apply(tr2.t).unify(Type.BOOL);
        comp = comp.compose(s2);

        return TypeResult.of(comp, Type.BOOL);

    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *  Depend on l -> v, where v = true or false
         */
        BoolValue v = (BoolValue) l.eval(s);
        if(v.b)
            return r.eval(s);
        else
            return v;
    }
}
