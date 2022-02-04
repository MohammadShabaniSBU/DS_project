package KDTree;

import Interfaces.NodeInterface;

public class Node<T> implements NodeInterface<Node<T>> {
    public Node<T> left;
    public Node<T> right;
    public int[] coordination;
    public T value;

    public Node(T value, int[] coordination) {
        this.value = value;
        this.coordination = coordination;
        left = right = null;
    }

    public int[] getCoordination() {
        return coordination;
    }

    public T getValue() {
        return value;
    }

    public boolean isEqual(Node<T> compare) {
        return false;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
