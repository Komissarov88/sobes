package com.komissarov.sobes.list.linked;

import com.komissarov.sobes.list.MyList;

public class LinkedList<T> implements MyList<T> {

    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        T data;
        Node prev;
        Node next;

        public Node(T data) {
            this.data = data;
        }

        private Node getNeighbour(int direction) {
            if (direction >= 0) {
                return next;
            } else {
                return prev;
            }
        }
    }

    private void throwIfNotInBounds(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("index out of bounds");
        }
    }

    private Node getNode(int idx) {
        throwIfNotInBounds(idx);

        int direction;
        int i;
        Node start;
        if (size - idx < size / 2) {
            start = last;
            direction = -1;
            i = size - 1;
        } else {
            start = first;
            direction = 1;
            i = 0;
        }
        while (i != idx) {
            i += direction;
            start = start.getNeighbour(direction);
        }
        return start;
    }

    private void removeNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }
        Node p = node.prev;
        Node n = node.next;
        if (p != null) {
            p.next = n;
        } else {
            first = n;
        }
        if (n != null) {
            n.prev = p;
        } else {
            last = p;
        }
        --size;
    }

    @Override
    public T get(int idx) {
        return getNode(idx).data;
    }

    @Override
    public int getId(T obj) {
        if (size == 0 || obj == null) {
            return -1;
        }
        int id = 0;
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (obj.equals(current.data)) {
                return id;
            }
            ++id;
            current = current.next;
        }
        return -1;
    }

    @Override
    public void add(int idx, T obj) {
        if (idx < 0 || idx > size) {
            throw new IllegalArgumentException("out of bounds");
        }
        Node insert = new Node(obj);
        if (idx == 0) {
            addFirst(obj);
            return;
        }
        if (idx == size) {
            addLast(obj);
            return;
        }
        Node atIdx = getNode(idx);
        insert.prev = atIdx.prev;
        insert.next = atIdx;
        atIdx.prev = insert;
        insert.prev.next = insert;

        ++size;
    }

    @Override
    public void addFirst(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("list does not accept null");
        }
        Node insert = new Node(obj);
        if (size == 0) {
            first = insert;
            last = insert;
            ++size;
            return;
        }
        insert.next = first;
        first.prev = insert;
        first = insert;
        ++size;
    }

    @Override
    public void addLast(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("list does not accept null");
        }
        Node insert = new Node(obj);
        if (size == 0) {
            first = insert;
            last = insert;
            ++size;
            return;
        }
        last.next = insert;
        insert.prev = last;
        last = insert;
        ++size;
    }

    @Override
    public boolean remove(T obj) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (obj.equals(node.data)) {
                removeNode(node);
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public void remove(int i) {
        removeNode(getNode(i));
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node node = first;
        for (int i = 0; i < size; i++) {
            sb.append(node.data).append(", ");
            node = node.next;
        }
        return sb.substring(0, sb.length()-2) + "]";
    }
}
