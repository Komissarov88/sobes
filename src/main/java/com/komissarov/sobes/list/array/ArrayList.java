package com.komissarov.sobes.list.array;

import com.komissarov.sobes.list.MyList;

import java.util.Arrays;

public class ArrayList<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] data = new Object[DEFAULT_CAPACITY];

    private void throwIfNotInBounds(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("index out of bounds");
        }
    }

    private void increaseCapacity() {
        Object[] newData = Arrays.copyOf(data, data.length * 2);
        data = newData;
    }

    @Override
    public T get(int i) {
        throwIfNotInBounds(i);
        return (T) data[i];
    }

    @Override
    public int getId(T obj) {
        if (size == 0 || obj == null) {
            return -1;
        }
        int id = 0;
        for (int i = 0; i < size; i++) {
            if (obj.equals(data[i])) {
                return id;
            }
            ++id;
        }
        return -1;
    }

    @Override
    public void addFirst(Object obj) {
        if (size + 1 >= data.length) {
            increaseCapacity();
        } else {
            System.arraycopy(data, 0, data, 1, size);
        }
        data[0] = obj;
        ++size;
    }

    @Override
    public void addLast(Object obj) {
        if (size == data.length) {
            increaseCapacity();
        }
        data[size] = obj;
        ++size;
    }

    @Override
    public void add(int idx, T obj) {
        if (idx == size) {
            addLast(obj);
            return;
        }
        throwIfNotInBounds(idx);
        if (size + 1 >= data.length) {
            increaseCapacity();
        } else {
            System.arraycopy(data, idx, data, idx + 1, size - idx);
        }
        data[idx] = obj;
        ++size;
    }

    @Override
    public boolean remove(T obj) {
        int id = getId(obj);
        if (id < 0) {
            return false;
        }
        remove(id);
        return true;
    }

    @Override
    public void remove(int i) {
        throwIfNotInBounds(i);
        if (i == size - 1) {
            data[i] = null;
            --size;
            return;
        }
        System.arraycopy(data, i+1, data, i, size - i);
        data[size-1] = null;
        --size;
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
        for (int i = 0; i < size; i++) {
            sb.append(data[i]).append(", ");
        }
        return sb.substring(0, sb.length()-2) + "]";
    }
}
