package simpl.typing;

public final class ListType extends Type {

    public Type t;

    public ListType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        /**
         *
         */
        return t.isEqualityType();
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        /**
         *  i. tv = list t
         *      [list t / tv]
         *  ii. list tv = list t
         *      tv = t
         *  iii. otherwise: type error
         */
        if(t instanceof TypeVar)
            return Substitution.of((TypeVar) t, this);
        else if (t instanceof ListType)
            return ((ListType) t).t.unify(this.t);
        else throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        /**
         *  if t contains tv
         */
        return t.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        /**
         *
         */
        return new ListType(this.t.replace(a, t));
    }

    public String toString() {
        return t + " list";
    }
}
