package com.komissarov.sobes.list.linked;

import com.komissarov.sobes.list.MyList;
import com.komissarov.sobes.list.array.ArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ArrayListTest {

    MyList<Integer> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.add(0,5);
        list.add(0,4);
        list.add(0,3);
        list.add(0,2);
        list.add(0,1);
    }

    @Test
    public void get() {
        assertEquals( "[1, 2, 3, 4, 5]", list.toString());
        assertEquals("get by id from beginning", 1, (int) list.get(0));
        assertEquals("get by id from middle", 3, (int) list.get(2));
        assertEquals("get by id from end", 5, (int) list.get(4));
    }

    @Test
    public void getId() {
        assertEquals("get by id from beginning", 0, list.getId(1));
        assertEquals("get by id from middle", 1, list.getId(2));
        assertEquals("get by id from end", 4, list.getId(5));
    }

    @Test
    public void add() {
        list.add(2, 9);
        assertEquals( "[1, 2, 9, 3, 4, 5]", list.toString());

        list.add(6, 99);
        assertEquals( "[1, 2, 9, 3, 4, 5, 99]", list.toString());

        list.addLast(8);
        list.addLast(9);
        list.addLast(10);
        list.addLast(11);
        assertEquals( "[1, 2, 9, 3, 4, 5, 99, 8, 9, 10, 11]", list.toString());
    }

    @Test
    public void addFirst() {
        list.addFirst(9);
        assertEquals( "[9, 1, 2, 3, 4, 5]", list.toString());
    }

    @Test
    public void addLast() {
        list.addLast(9);
        assertEquals( "[1, 2, 3, 4, 5, 9]", list.toString());
    }

    @Test
    public void remove() {
        list.remove(Integer.valueOf(1));
        assertEquals("remove from beginning", "[2, 3, 4, 5]", list.toString());

        setUp();
        list.remove(Integer.valueOf(4));
        assertEquals("remove from middle", "[1, 2, 3, 5]", list.toString());

        setUp();
        list.remove(Integer.valueOf(5));
        assertEquals("remove from end", "[1, 2, 3, 4]", list.toString());

        setUp();
        list.remove(4);
        assertEquals("remove from end", "[1, 2, 3, 4]", list.toString());

        setUp();
        list.remove(0);
        assertEquals("remove from beginning", "[2, 3, 4, 5]", list.toString());

        setUp();
        list.remove(2);
        assertEquals("remove from middle", "[1, 2, 4, 5]", list.toString());
    }

    @Test
    public void getSize() {
        list.addLast(8);
        list.addLast(9);
        list.addLast(10);
        list.addLast(11);
        list.addLast(11);
        list.addLast(11);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        assertEquals( 0, list.getSize());
        list.addFirst(77);
        assertEquals( 1, list.getSize());
    }
}