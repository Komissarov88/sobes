package com.komissarov.sobes.list;

public interface MyList<T> {

    T get(int i);
    int getId(T obj);
    void addFirst(T obj);
    void addLast(T obj);
    void add(int i, T obj);
    boolean remove(T obj);
    void remove(int i);
    int getSize();
}
