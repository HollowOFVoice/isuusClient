package com.example.isuusclient.entity;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
                               surname +
                        " " + name  +
                        " " + lastname  +
                          ", " + group +
                                       ", "+ assessmen +
                        ", " + recordBook ;
    }
}
