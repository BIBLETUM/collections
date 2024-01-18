import java.util.Iterator;

public class CarLinkedList<T> implements CarList<T> {
    private Node last;
    private Node first;
    private int size = 0;

    private class Node {
        private Node previous;
        private Node next;
        private T value;

        private Node(Node previous, Node next, T value) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node node = first;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                T car = node.value;
                node = node.next;
                return car;
            }
        };
    }

    @Override
    public boolean contains(T car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if(node.value.equals(car)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean add(T car) {
        if (size == 0) {
            Node newNode = new Node(null, null, car);
            first = newNode;
            last = newNode;
        } else {
            Node oldNode = last;
            last = new Node(oldNode, null, car);
            oldNode.next = last;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(T car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            return add(car);
        } else {
            Node oldNode = getNodeByIndex(index);
            Node oldNodePrev = oldNode.previous;
            Node newNode = new Node(oldNodePrev, oldNode, car);
            if (oldNodePrev != null){
                oldNodePrev.next = newNode;
            }
            else{
                first = newNode;
            }
            oldNode.previous = newNode;
            size++;
            return true;
        }
    }

    @Override
    public boolean remove(T car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if(node.value.equals(car)){
                return removeAt(i);
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIndex(index);
        Node nodeDelete;
        Node nodeNext;
        Node nodePrev;
        if(index == 0){
            nodeDelete = first;
            nodeNext = nodeDelete.next;
            nodeNext.previous = null;
            first = nodeNext;
        } else if (index == size - 1) {
            nodeDelete = last;
            nodePrev = nodeDelete.previous;
            nodePrev.next = null;
            last = nodePrev;
        }
        else{
            nodeDelete = getNodeByIndex(index);
            nodePrev = nodeDelete.previous;
            nodeNext = nodeDelete.next;
            nodeNext.previous = nodePrev;
            nodePrev.next = nodeNext;
        }
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node getNodeByIndex(int index) {
        checkIndex(index);
        Node oldNode = first;
        for (int i = 0; i < index; i++) {
            oldNode = oldNode.next;
        }
        return oldNode;
    }
}
