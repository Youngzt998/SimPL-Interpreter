package simpl.interpreter;

import simpl.parser.Symbol;

public class Env {

    private final Env E;
    private final Symbol x;
    private final Value v;

    private Env() {
        E = null;
        x = null;
        v = null;
    }

    public static Env empty = new Env() {
        public Value get(Symbol y) {
            return null;
        }

        public Env clone() {
            return this;
        }
    };

    public Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
    }

    public Value get(Symbol y) {
        // TODO
        /**
            Check one by one like a list
        */
        assert x != null;
        if(y.toString().equals(x.toString()))
            return v;
        else if(E!=null)
            return E.get(y);
        else
            return null;

    }

    public Env clone() {
        // TODO
        /**
            Just copy everything
        */
        return new Env(E, x, v);
    }
}
