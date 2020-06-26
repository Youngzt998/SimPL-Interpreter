package simpl.typing;

final class IntType extends Type {

    protected IntType() {
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
         *  i.      Int = Int, then useless, just return an identity
         *  ii.     tv = Int, then return this unification
         *  iii.    others = Int, then find a type error
         */
        if (t instanceof IntType)
            return Substitution.IDENTITY;
        else if (t instanceof TypeVar)
            return Substitution.of((TypeVar) t, this);
        else throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        /**
            int type cannot contain any type
        */
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        /**
            INT[a/t] = INT
        */
        return Type.INT;
    }

    public String toString() {
        return "int";
    }
}
