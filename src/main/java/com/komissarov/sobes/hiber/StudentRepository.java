package com.komissarov.sobes.hiber;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepository {

    public Student getById(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("id less then zero");
        }
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Student> q = session.createQuery("From Student where id = :id", Student.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    public void deleteStudentById(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("id less then zero");
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(getById(id));
        session.getTransaction().commit();
    }

    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("delete From Student");
        q.executeUpdate();
        session.getTransaction().commit();
    }

    public long saveStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student is null");
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        return student.getStudentId();
    }

    public void saveStudent(List<Student> students) {
        if (students == null) {
            throw new IllegalArgumentException("student is null");
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        students.forEach(session::save);
        session.getTransaction().commit();
    }

    public long updateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student is null");
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        return student.getStudentId();
    }

    public List<Student> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Student> q = session.createQuery("From Student", Student.class);
        return q.list();
    }
}
