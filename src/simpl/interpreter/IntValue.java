package simpl.interpreter;

public class IntValue extends Value {

    public final int n;

    public IntValue(int n) {
        this.n = n;
    }

    public String toString() {
        return "" + n;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        /**
         *  other must be a subtype of IntValue
         */
        if(other instanceof  IntValue)
            return n == ((IntValue)other).n;
        else
            // TODO:
            /*
                Q: return false or throw exception ?
                A: ?
            */
            return false;
    }
}
