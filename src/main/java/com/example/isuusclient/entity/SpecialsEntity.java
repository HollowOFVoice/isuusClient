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

public class SpecialsEntity {

    private Long id;

    private String special;

    @Override
    public String toString() {
        return
                " " + special ;
    }
}
