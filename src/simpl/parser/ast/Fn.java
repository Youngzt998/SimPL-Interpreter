package simpl.parser.ast;

import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Fn extends Expr {

    public Symbol x;
    public Expr e;

    public Fn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(fn " + x + "." + e + ")";
    }


    @Override
    public Fn replace(Symbol x, Expr e) {
        if(this.x.toString().equals(x.toString())){
            return this;
        }
        else return new Fn(this.x, this.e.replace(x, e));
    }


    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO

        /**
         *      Rule CT-FN
         *      G, x:a |- e:t, q
         *      -------------------------------------
         *      G |- fn x => e : a -> t, q
         *
         *      e.g.
         *      ., x: a |- x + 1: int, {a = int}
         *      --------------------------
         *      .|- fn x => x + 1: int -> int, {a = int}
         */
        TypeVar a = new TypeVar(true);
        TypeResult tr = e.typecheck(TypeEnv.of(E, x, a));
        ArrowType arrow = new ArrowType(tr.s.apply(a), tr.s.apply(tr.t));

        return TypeResult.of(tr.s, arrow);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *  Just evaluate to a FunValue
         */
        return new FunValue(s.E, x, e);
    }
}
