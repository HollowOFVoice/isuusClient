package com.example.isuusclient.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AssessmenEntity {


    private  Long id;
    private int assessmen;

    private LessonsEntity lesson;
    private List<StudentsEntity> students;

    @Override
    public String toString() {
        return
                "" + assessmen + ' ' + lesson + ' ' + students + ' ';
    }
}
