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

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    public Loop replace(Symbol x, Expr e) {
        return new Loop(e1.replace(x, e), e2.replace(x, e));
    }


    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        /**
         *
         */
        TypeResult tr1 = e1.typecheck(E);
        TypeEnv NewE = tr1.s.compose(E);
        TypeResult tr2 = e2.typecheck(NewE);

        Substitution comp = tr2.s.compose(tr1.s);
        Substitution s1 = comp.apply(tr1.t).unify(Type.BOOL);

        comp = comp.compose(s1);

        return TypeResult.of(comp, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        /**
         *  if e1 evaluate to true, then evaluate e2 once (may change the State s)
         *  else just evaluate to UnitValue
         */
        BoolValue v1 = (BoolValue) e1.eval(s);
        if(v1.b){
            e2.eval(s);
            try{
                return new Loop(e1, e2).eval(s);
            }catch (StackOverflowError e){
                throw new RuntimeError("runtime error: stack overflow");
            }

        }
        else
            return Value.UNIT;
    }
}
