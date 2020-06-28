package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    public Rec replace(Symbol x, Expr e) {
        if(this.x.toString().equals(x.toString()))
            return this;
        else return new Rec(this.x, this.e.replace(x, e));
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        /**
         *      Rule CT-REC:
         *      G, x: a |- e: t, q
         *      -------------------------------
         *      G |- rec x => e: t, q
         */
        TypeVar a = new TypeVar(true);
        TypeResult tr = e.typecheck(TypeEnv.of(E, x, a));
        Substitution s = tr.t.unify(a);
        s = tr.s.compose(s);
        return TypeResult.of(tr.s, s.apply(tr.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RecValue v = new RecValue(s.E, x, e);
//        try {
            return e.eval(State.of(new Env(s.E, x, v), s.M, s.p));
//        }catch (StackOverflowError e){
//            throw new RuntimeError("runtime error: stack overflow");
//        }

    }
}
