package simpl.typing;

import simpl.parser.Symbol;

public abstract class Substitution {

    public abstract Type apply(Type t);

    private static final class Identity extends Substitution {
        public Type apply(Type t) {
            return t;
        }

        public String toString(){
            return "Id";
        }
    }

    private static final class Replace extends Substitution {
        private TypeVar a;
        private Type t;

        public Replace(TypeVar a, Type t) {
            this.a = a;
            this.t = t;
        }

        public Type apply(Type b) {
            return b.replace(a, t);
        }

        public String toString(){
            return a + " = " + t;
        }
    }

    private static final class Compose extends Substitution {
        private Substitution f, g;

        public Compose(Substitution f, Substitution g) {
            this.f = f;
            this.g = g;
        }

        public Type apply(Type t) {
            /**
             *  The sequence is principle
             */
            return g.apply(f.apply(t));
        }

        public String toString(){
            return f + ", " + g;
        }
    }

    public static final Substitution IDENTITY = new Identity();

    public static Substitution of(TypeVar a, Type t) {
        return new Replace(a, t);
    }

    public Substitution compose(Substitution inner) {
        return new Compose(this, inner);
    }

    public TypeEnv compose(final TypeEnv E) {
        return new TypeEnv() {
            public Type get(Symbol x) throws TypeError {
                try {
                    return apply(E.get(x));
                }catch (TypeError e){
                    throw e;
                }
            }
        };
    }
}
