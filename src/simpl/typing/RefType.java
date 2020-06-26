package simpl.typing;

public final class RefType extends Type {

    public Type t;

    public RefType(Type t) {
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
         *  i. tv = ref t
         *  ii. ref tv = ref t
         *  iii. otherwise: type error
         */
        if(t instanceof TypeVar)
            return Substitution.of((TypeVar) t, this);
        else if (t instanceof RefType)
            return ((RefType) t).t.unify(this.t);
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
        return new RefType(this.t.replace(a, t));
    }

    public String toString() {
        return t + " ref";
    }
}
