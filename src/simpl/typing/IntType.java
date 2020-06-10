package simpl.typing;

final class IntType extends Type {

    protected IntType() {
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        return null;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        /*
            int type cannot contain any type
        */
        //
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        /*
            INT[a/t] = INT
        */
        return Type.INT;
    }

    public String toString() {
        return "int";
    }
}
