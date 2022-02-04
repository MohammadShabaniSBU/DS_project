package ArrayList;

import Interfaces.FactoryInterface;
import Interfaces.NodeInterface;

public class ArrayList<T extends NodeInterface<T>> {
    private T[] repo;
    private final FactoryInterface<T> builder;
    private int size;
    private int capacity;

    public ArrayList(FactoryInterface<T> builder) {
        this.size = 0;
        this.capacity = 2;
        this.builder = builder;
        repo = builder.getArrayObject(capacity);
    }

    public void insert(T value) {
        if (size == capacity) {
            addCapacity();
        }

        repo[size++] = value;
    }

    public T getByNode(T val) {
        for (int i = 0; i < size; i++)
            if (val.isEqual(repo[i])) {
                return repo[i];
            }
        return null;
    }

    protected void addCapacity() {
        T[] newRepo = builder.getArrayObject(2 * capacity);

        for (int i = 0; i < size; i++) {
            newRepo[i] = repo[i];
        }

        repo = newRepo;
        capacity *= 2;
    }

    public void print() {
        if (size == 0) {
            System.out.println("There is nothing to print");
        }

        for (int i = 0; i < size; i++) {
            System.out.println(repo[i].toString());
        }
    }

    public int getSize() {
        return size;
    }
}
