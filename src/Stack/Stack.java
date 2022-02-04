package Stack;

public class Stack<T> {
    private Node<T> last;
    int size;

    public Stack() {
        last = null;
        size = 0;
    }

    public void push(T value) {
        Node<T> node = new Node<T>(value);

        node.setBefore(last);
        last = node;
        size += 1;
    }

    public T pop() {
        if (last == null)
            return null;

        T tmp = last.getValue();
        last = last.getBefore();
        size -= 1;
        return tmp;
    }

    public int getSize() {
        return size;
    }
}
