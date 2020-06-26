package simpl.typing;

import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        // TODO
        /**
         *  i. tv = tv
         *      Just eliminate it
         *  ii. tv = f(tv)
         *      Type error
         *  iii. tv = tv1/int/bool/...
         */
        if (t instanceof TypeVar){
            if(((TypeVar) t).name.equals(this.name)){
                return Substitution.IDENTITY;
            }
//            else if (!this.isEqualityType())
//                return Substitution.of((TypeVar)t, this);
        }
        else if (t.contains(this))
            throw new TypeCircularityError();
        return Substitution.of(this, t);
    }

    public String toString() {
        return "" + name;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        /**
         *  Return true only when they are the same type variable
         */
        return name.equals(tv.name);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        if (name.equals(a.name))
            return t;
        else return this;
    }
}
