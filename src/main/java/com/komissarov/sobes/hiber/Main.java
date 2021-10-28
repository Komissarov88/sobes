package com.komissarov.sobes.hiber;

import com.oblac.nomen.Nomen;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();
        repository.deleteAll();

        final int STUDENTS_COUNT = 10;
        Random rnd = new Random();
        List<Student> students = new ArrayList<>(STUDENTS_COUNT);
        for (int i = 0; i < STUDENTS_COUNT; i++) {
            Student student = new Student();
            student.setName(Nomen.randomName());
            student.setMark(rnd.nextInt(5));
            students.add(student);
        }

        repository.saveStudent(students);

        List<Student> resultList = repository.getAll();
        System.out.println("total sudents:" + resultList.size());

        for (Student s : resultList) {
            System.out.println("student : " + s);
        }
    }
}
