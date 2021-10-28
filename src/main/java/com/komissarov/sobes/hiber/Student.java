package com.komissarov.sobes.hiber;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Student")
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long studentId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "mark", unique = true, length = 25)
    private Integer mark;
}