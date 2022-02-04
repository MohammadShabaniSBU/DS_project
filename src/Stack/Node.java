package Stack;

public class Node<T> {
    private T value;
    private Node<T> before;

    public Node(T value) {
        this.value = value;
        this.before = null;
    }

    public void setBefore(Node<T> before) {
        this.before = before;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getBefore() {
        return before;
    }
}
