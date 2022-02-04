package KDTree;

import ArrayList.ArrayList;
import Factory.KNodeFactory;

public class KDTree<T> {
    private Node<T> root = null;
    private int size = 0;

    public void insert(T value, int[] coordination) throws Exception {
        Node<T> node = new Node<T>(value, coordination);

        if (root == null) {
            root = node;
            return;
        }

        root = insertNode(root, node, 0);
        size += 1;
    }

    public int getSize() {
        return size;
    }

    protected Node<T> insertNode(Node<T> root, Node<T> node, int depth) throws Exception {
        if (root == null) {
            return node;
        }

        int[] rootCor = root.getCoordination();
        int[] nodeCor = node.getCoordination();

        if (rootCor[0] == nodeCor[0] && rootCor[1] == nodeCor[1]) {
            throw new Exception("Error: The coordination is already taken");
        }

        depth %= 2;

        if (nodeCor[depth] < rootCor[depth]) {
            root.left = insertNode(root.left, node, depth + 1);
        } else {
            root.right = insertNode(root.right, node, depth + 1);
        }
        return root;
    }

    public ArrayList<Node<T>> getNodesIn(int[] downLeft, int[] upRight) {
        ArrayList<Node<T>> res = new ArrayList<Node<T>>(new KNodeFactory<T>());

        findNodesIn(root, 0, downLeft, upRight, res);

        return res;
    }

    protected void findNodesIn(Node<T> root, int depth, int[] downLeft, int[] upRight, ArrayList<Node<T>> res) {
        if (root == null)
            return;

        depth %= 2;

        int[] rootCor = root.getCoordination();

        if (rootCor[depth] >= downLeft[depth]) {
            findNodesIn(root.left, depth + 1, downLeft, upRight, res);
        }

        if (rootCor[depth] <= upRight[depth]) {
            findNodesIn(root.right, depth + 1, downLeft, upRight, res);
        }

        if (rootCor[0] >= downLeft[0] && rootCor[0] <= upRight[0] &&
                rootCor[1] >= downLeft[1] && rootCor[1] <= upRight[1]) {
            res.insert(root);
        }
    }

    public T getNearest(int[] coordination) {
        if (size == 0)
            return null;

        return findNearest(coordination, root, root, 0).getValue();
    }

    public Node<T> findNearest(int[] coordination, Node<T> root, Node<T> nearest, int depth) {
        if (root == null)
            return nearest;

        int[] nearestCor = nearest.getCoordination();
        int[] rootCor = root.getCoordination();
        Node<T> leftNearest = nearest;
        Node<T> rightNearest = nearest;
        int nearestDistance = calcDistance(nearestCor, coordination);
        int rootDistance = calcDistance(rootCor, coordination);

        if (rootDistance < nearestDistance) {
            nearest = root;
            nearestDistance = rootDistance;
        }

        depth %= 2;

        if (coordination[depth] < rootCor[depth]) {
            leftNearest = findNearest(coordination, root.left, nearest, depth + 1);
        } else {
            rightNearest = findNearest(coordination, root.right, nearest, depth + 1);
        }

        int leftNearestDistance = calcDistance(leftNearest.getCoordination(), coordination);
        int rightNearestDistance = calcDistance(rightNearest.getCoordination(), coordination);

        if (leftNearestDistance < nearestDistance) {
            nearest = leftNearest;
            nearestDistance = leftNearestDistance;
        }

        if (rightNearestDistance < nearestDistance) {
            nearest = rightNearest;
            nearestDistance = rightNearestDistance;
        }

        if (Math.abs(coordination[depth] - rootCor[depth]) < nearestDistance) {
            if (coordination[depth] < rootCor[depth]) {
                rightNearest = findNearest(coordination, root.right, nearest, depth + 1);
            } else {
                leftNearest = findNearest(coordination, root.left, nearest, depth + 1);
            }

            leftNearestDistance = calcDistance(leftNearest.getCoordination(), coordination);
            rightNearestDistance = calcDistance(rightNearest.getCoordination(), coordination);

            if (leftNearestDistance < nearestDistance) {
                nearest = leftNearest;
                nearestDistance = leftNearestDistance;
            }

            if (rightNearestDistance < nearestDistance) {
                nearest = rightNearest;
                nearestDistance = rightNearestDistance;
            }
        }

        return nearest;
    }

    public ArrayList<Node<T>> getBanksInRadius(int[] center, int radius) {
        ArrayList<Node<T>> res = new ArrayList<Node<T>>(new KNodeFactory<T>());

        getBanksInRadiusRec(center, radius, root, res, 0);

        return res;
    }

    protected void getBanksInRadiusRec(int[] center,
                                       int radius,
                                       Node<T> currentNode,
                                       ArrayList<Node<T>> res,
                                       int depth)
    {
        if (currentNode == null)
            return;

        depth %= 2;

        if (calcDistance(center, currentNode.getCoordination()) <= radius * radius) {
            res.insert(currentNode);
        }

        if (currentNode.getCoordination()[depth] - center[depth] >= (-1 * radius)) {
            getBanksInRadiusRec(center, radius, currentNode.left, res, depth + 1);
        }

        if (currentNode.getCoordination()[depth] - center[depth] <= radius) {
            getBanksInRadiusRec(center, radius, currentNode.right, res, depth + 1);
        }
    }

    public T findWithCoordination(int[] coordination) {
        return findWithCoordinationRec(coordination, root, 0);
    }

    public T findWithCoordinationRec(int[] coordination, Node<T> root, int depth) {
        if (root == null)
            return null;
        int[] rootCor = root.getCoordination();

        if (coordination[0] == rootCor[0] && coordination[1] == rootCor[1])
            return root.getValue();

        depth %= 2;
        if (coordination[depth] < rootCor[depth])
            return findWithCoordinationRec(coordination, root.left, depth + 1);
        else
            return findWithCoordinationRec(coordination, root.right, depth + 1);
    }

    public Node<T> minNode(Node<T> x, Node<T> y, Node<T> z, int depth) {
        Node<T> res = x;
        depth %= 2;

        if (y != null && y.getCoordination()[depth] < res.getCoordination()[depth])
            res = y;
        if (z != null && z.getCoordination()[depth] < res.getCoordination()[depth])
            res = z;
        return res;
    }

    public Node<T> findMinRec(Node<T> root, int d, int depth) {
        if (root == null)
            return null;

        depth %= 2;

        if (depth == d) {
            if (root.left == null)
                return root;
            return findMinRec(root.left, d, depth + 1);
        }

        return minNode(
                root,
                findMinRec(root.left, d, depth + 1),
                findMinRec(root.right, d, depth + 1), d
        );
    }

    public Node<T> findMin(Node<T> root, int d) {
        return findMinRec(root, d, 0);
    }


    public void removeNode(int[] coordination) {
        removeNodeRec(coordination, root, 0);
    }

    public Node<T> removeNodeRec(int[] coordination, Node<T> root, int depth) {
        if (root == null)
            return null;

        depth %= 2;
        int[] rootCor = root.getCoordination();

        if (rootCor[0] == coordination[0] && rootCor[1] == coordination[1]) {
            if (root.right != null) {
                Node<T> min = findMin(root.right, depth);

                root.coordination = min.coordination;
                root.value = min.value;

                root.right = removeNodeRec(min.coordination, root.left,  depth + 1);
            } else {
                return null;
            }
            return root;
        }

        if (coordination[depth] < rootCor[depth])
            root.left = removeNodeRec(coordination, root.left, depth + 1);
        else
            root.right = removeNodeRec(coordination, root.right, depth + 1);

        return root;
    }

    protected int calcDistance(int[] point1, int[] point2) {
        int x = point1[0] - point2[0];
        int y = point1[1] - point2[1];
        return x * x + y * y;
    }

    public void print() {
        printRec(root);
    }

    protected void printRec(Node<T> node) {

        if (node.left != null)
            printRec(node.left);

        System.out.println(node.getValue());

        if (node.right != null)
            printRec(node.right);
    }
}
