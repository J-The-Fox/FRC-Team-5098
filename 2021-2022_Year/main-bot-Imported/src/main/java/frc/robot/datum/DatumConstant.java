package frc.robot.datum;

public class DatumConstant<T> implements DatumSource<T>
{
    private final T t;

    public DatumConstant(T val)
    {
        t = val;
    }
    
    @Override
    public T get() {
        return t;
    }

}