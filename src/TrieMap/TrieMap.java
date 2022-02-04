package TrieMap;

import Interfaces.NodeInterface;

public class TrieMap<T> {
    private final Node<T> root;
    private int size;

    public TrieMap() {
        this.root = new Node<T>('^');
        size = 0;
    }

    public void insert(String key, T value) {
        Node<T> node = root;

        for (int i = 0; i < key.length(); i++) {
            node = node.getOrCreateKidWithChar(key.charAt(i));
        }

        // here we need to check is node already a leaf or not. if it is the key is repeated.
        node.setLeaf(true);
        node.setValue(value);
        size++;
    }

    public T get(String key) {
        Node<T> node = root;
        for (int i = 0; i < key.length(); i++) {
            node = node.getKidWithChar(key.charAt(i));
            if (node == null) {
                return null;
            }
        }

        if (node.isLeaf()) {
            return node.getValue();
        } else {
            return null;
        }
    }
}
