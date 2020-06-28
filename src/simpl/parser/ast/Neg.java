package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Neg extends UnaryExpr {

    public Neg(Expr e) {
        super(e);
    }

    public String toString() {
        return "~" + e;
    }


    public Neg replace(Symbol x, Expr e) {
        return new Neg(this.e.replace(x, e));
    }


    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr = e.typecheck(E);
        Substitution s = tr.t.unify(Type.INT);
        return TypeResult.of(s, Type.INT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new IntValue( - ((IntValue)e.eval(s)).n);
    }
}
