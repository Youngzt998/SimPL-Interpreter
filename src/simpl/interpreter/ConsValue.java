package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    private int length(){
        if (v2 instanceof ConsValue)
            return 1 + ((ConsValue) v2).length();
        else return 1;
    }

    public String toString() {
        // TODO
        /**
         *  v1 :: v2 :: ... :: vn :: Nil
         */

        return v1.toString() + "::" + v2.toString();
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        /**
         *  v1 == other.v1 && v2 == other.v2
         *  v1 :: v2 :: ... :: vn :: Nil == v1' :: v2' :: ... vn' :: Nil
         */
        if(other instanceof ConsValue)
            return v1.equals(((ConsValue) other).v1) && v2.equals(((ConsValue) other).v2);
        else return false;
    }
}
