package simpl.typing;

import simpl.parser.ast.Sub;

import javax.swing.*;

public final class ArrowType extends Type {

    public Type t1, t2;

    public ArrowType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        /**
         *
         */
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        /**
         *  i. tv = t1 -> t2
         *      [(t1 -> t2) / tv]
         *  ii. tv1 -> tv2 = t1 -> t2
         *      tv1 = t1, tv2 = t2
         *  iii. int / bool / ... = t1 -> t2
         *      type error
         */
        if (t instanceof TypeVar)
            return Substitution.of((TypeVar) t, this);
        else if (t instanceof ArrowType){
            Substitution s1 = ((ArrowType) t).t1.unify(this.t1);
            Substitution s2 = s1.apply(((ArrowType) t).t2).unify(s1.apply(this.t2));
            return s1.compose(s2);
        }
        else throw new TypeMismatchError();

    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        /**
         *  One of them contains tv
         */
        return t1.contains(tv) || t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        /**
         *  (t1 -> t2)[t/a] = (t1[t/a] -> t2[t/a])
         */
        return new ArrowType(t1.replace(a, t), t2.replace(a, t));
    }

    public String toString() {
        return "(" + t1 + " -> " + t2 + ")";
    }
}
