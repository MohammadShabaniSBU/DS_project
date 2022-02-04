package Factory;

import Interfaces.FactoryInterface;
import KDTree.Node;

public class KNodeFactory<T> implements FactoryInterface<Node<T>> {
    @Override
    public Node<T> getObject() {
        int[] cor = {1, 2};
        return new Node<T>(null, cor);
    }

    @Override
    public Node<T>[] getArrayObject(int size) {
        return new Node[size];
    }
}
