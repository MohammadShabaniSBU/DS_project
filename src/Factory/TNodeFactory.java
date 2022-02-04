package Factory;

import TrieMap.Node;
import Interfaces.FactoryInterface;

public class TNodeFactory<T> implements FactoryInterface<Node<T>> {
    @Override
    public Node<T> getObject() {
        return new Node<T>('^');
    }

    @Override
    public Node<T>[] getArrayObject(int size) {
        return new Node[size];
    }
}
