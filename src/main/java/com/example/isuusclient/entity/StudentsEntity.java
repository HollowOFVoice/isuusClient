package com.example.isuusclient.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

public class StudentsEntity {

    private Long id;


    private AssessmenEntity assessmen;

    private GroupsEntity group;


    private String name;

    private String surname;


    private String lastname;


private  Long recordBook;

    @Override
    public String toString() {
        return
                assessmen +
                " " + group +
                " " + name  +
                " " + surname +
                " " + lastname  +
                " " + recordBook ;
    }
}
