package simpl.interpreter;

class NilValue extends Value {

    protected NilValue() {
    }

    public String toString() {
        return "nil";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        /**
         *  Only one Nil
         */
        if(other instanceof NilValue)
            return true;
        else return false;

    }
}
