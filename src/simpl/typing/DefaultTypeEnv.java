package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO
        /**
         *  Add pre-defined function types
         */
        E = new TypeEnv() {
            @Override
            public Type get(Symbol x) throws TypeError {
                if (x.toString().equals("fst")){

                    TypeVar tv1 = new TypeVar(true);
                    TypeVar tv2 = new TypeVar(true);
                    return new ArrowType(new PairType(tv1, tv2), tv1);
                }
                else if (x.toString().equals("snd")){

                    TypeVar tv1 = new TypeVar(true);
                    TypeVar tv2 = new TypeVar(true);
                    return new ArrowType(new PairType(tv1, tv2), tv2);
                }if (x.toString().equals("hd")){
                    TypeVar tv = new TypeVar(true);
                    ListType lt = new ListType(tv);
                    return new ArrowType(lt, tv);
                }if (x.toString().equals("tl")){
                    TypeVar tv = new TypeVar(true);
                    ListType lt = new ListType(tv);
                    return new ArrowType(lt, new ListType(tv));
                }else if (x.toString().equals("iszero")){
                    return new ArrowType(Type.INT, Type.BOOL);
                } else if (x.toString().equals("pred")){
                    return new ArrowType(Type.INT, Type.INT);
                } else if (x.toString().equals("succ")){
                    return new ArrowType(Type.INT, Type.INT);
                } else throw new TypeError("variable not found in current environment");

            }
        };
    }

    @Override
    public Type get(Symbol x) throws TypeError {
        try {
            return E.get(x);
        }catch (TypeError e){
            throw e;
        }

    }
}
