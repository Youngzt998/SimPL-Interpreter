package simpl.typing;

final class BoolType extends Type {

    protected BoolType() {
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        /**
         *  Comparable
         */
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        /**
         *  i.      bool = bool, then useless, just return an identity
         *  ii.     tv = bool, then return this unification
         *  iii.    others = bool, then find a type error
         */
        if (t instanceof BoolType)
            return Substitution.IDENTITY;
        else if (t instanceof TypeVar)
            return Substitution.of((TypeVar) t, this);
        else throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        /**
         bool type cannot contain any type
         */
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        /**
         INT[a/t] = INT
         */
        return Type.BOOL;
    }

    public String toString() {
        return "bool";
    }
}
