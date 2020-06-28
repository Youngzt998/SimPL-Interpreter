package simpl.typing;

import simpl.parser.Symbol;

public abstract class TypeEnv {

    public abstract Type get(Symbol x) throws TypeError;

    public static TypeEnv of(final TypeEnv E, final Symbol x, final Type t) {
        return new TypeEnv() {
            public Type get(Symbol x1) throws TypeError {
                if (x == x1) return t;
                try {
                    return E.get(x1);
                }catch (TypeError e){
                    throw e;
                }

            }

            public String toString() {
                return x + ":" + t + ";" + E;
            }
        };
    }

    public static final TypeEnv empty = new TypeEnv() {
        @Override
        public Type get(Symbol x) {
            return null;
        }
    };
}
