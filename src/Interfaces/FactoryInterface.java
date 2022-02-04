package Interfaces;

public interface FactoryInterface<T> {
    public T getObject();
    public T[] getArrayObject(int size);
}
