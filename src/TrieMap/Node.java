package TrieMap;


import ArrayList.ArrayList;
import Factory.TNodeFactory;
import Interfaces.NodeInterface;

public class Node<T> implements NodeInterface<Node<T>> {
    private char ch;
    private boolean isLeaf;
    private ArrayList<Node<T>> children;
    private Node<T> parent;
    private T value;

    public Node(char ch) {
        this.ch = ch;
        this.isLeaf = false;
        this.value = null;
        children = new ArrayList<Node<T>>(new TNodeFactory<T>());
        parent = null;
    }

    public void setLeaf(boolean leaf) {
        this.isLeaf = leaf;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public char getCh() {
        return ch;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public T getValue() {
        return value;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void insertChild(Node<T> val) {
        children.insert(val);
    }

    public Node<T> getKidWithChar(char ch) {
        Node<T> node = new Node<T>(ch);

        return children.getByNode(node);
    }

    public Node<T> getOrCreateKidWithChar(char ch) {
        Node<T> node = new Node<T>(ch);

        Node<T> oldNode = children.getByNode(node);

        if (oldNode != null) {
            return oldNode;
        }

        children.insert(node);
        return node;
    }

    public int countOfKids() {
        return children.getSize();
    }
    public boolean isEqual(Node<T> compare) {
        return this.getCh() == compare.getCh();
    }
}
