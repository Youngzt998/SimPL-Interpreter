package simpl.interpreter;

class UnitValue extends Value {

    protected UnitValue() {
    }

    public String toString() {
        return "unit";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if(other instanceof  UnitValue)
            return true;
        else
            // TODO:
            /*
                Q: return false or throw exception ?
                A: ?
            */
            return false;
    }
}
