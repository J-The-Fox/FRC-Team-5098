package frc.robot.datum;

import java.util.function.BiFunction;

public class DatumReducer<T,U> implements DatumSource<U> {

    private DatumSource<T> source;
    private DatumSource<T> source2;
    private BiFunction<T, T, U> BiFunc;
    
    public DatumReducer(DatumSource<T> source, DatumSource<T> source2, BiFunction<T, T, U> mapFunc)
    {
        this.source = source;
        this.source2 = source2;
        this.BiFunc = mapFunc;
    }

    @Override
    public U get() {
        return BiFunc.apply(source.get(),source2.get());
    }   
}