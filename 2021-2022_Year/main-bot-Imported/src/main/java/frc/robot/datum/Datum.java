package frc.robot.datum;

public class Datum<T> implements DatumSink<T>, DatumSource<T> {
    T foo;

    @Override
    public T get() {
        return foo;
    }

    @Override
    public void set(T t) {
        foo = t;
    }
}