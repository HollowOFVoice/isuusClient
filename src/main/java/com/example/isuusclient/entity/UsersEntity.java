package com.example.isuusclient.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class UsersEntity {

    private Long id;
    private String username;
    private String password;

    @Override
    public String toString() {
        return
                " " + username  +
                " " + password ;
    }
}
