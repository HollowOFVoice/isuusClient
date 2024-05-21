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

public class LessonsEntity {

    private Long id;


    private String lesson;

    private SpecialsEntity special;

    @Override
    public String toString() {
        return
                " " + lesson + ' ' +
                "  " + special ;
    }
}

// может просто генерировать среднюю оценку в ццелом у студента, не деля его по специальностям
// и группам
