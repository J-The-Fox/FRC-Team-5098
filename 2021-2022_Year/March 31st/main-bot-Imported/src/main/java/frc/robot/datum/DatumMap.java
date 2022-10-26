package frc.robot.datum;

import java.util.function.Function;

public class DatumMap<T, U> implements DatumSource<U>
{
    private DatumSource<T> source;
    private Function<T, U> mapFunc;
    
    public DatumMap(DatumSource<T> source, Function<T, U> mapFunc)
    {
        this.source = source;
        this.mapFunc = mapFunc;
    }

    @Override
    public U get() {
        return mapFunc.apply(source.get());
    }
}