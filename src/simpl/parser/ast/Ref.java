package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr = e.typecheck(E);

        /**
         *
         */
        return TypeResult.of(tr.s, new RefType(tr.s.apply(tr.t)));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *                      [E-REF]
         *
         */
        int i = s.p.get();
        s.p.set(i + 1);
        Value v = e.eval(s);
        s.M.put(i + 1, v);
        return new RefValue(i + 1);
    }
}
