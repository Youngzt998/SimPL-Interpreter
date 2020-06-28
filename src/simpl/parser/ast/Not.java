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

public class Not extends UnaryExpr {

    public Not(Expr e) {
        super(e);
    }

    public String toString() {
        return "(not " + e + ")";
    }


    public Not replace(Symbol x, Expr e) {
        return new Not(this.e.replace(x, e));
    }


    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        /**
         *  Rule CT-NOT:
         *      G |- e: t, q
         *      --------------------------------
         *      G |- Not e: bool, q U {t = bool}
         */
        TypeResult tr = e.typecheck(E);
        Substitution s = tr.t.unify(Type.BOOL);

        return TypeResult.of(tr.s.compose(s), Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new BoolValue(!((BoolValue)e.eval(s)).b);
    }
}
