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


public class GroupsEntity {

    private  Long id;

    private  String groups;

    @Override
    public String toString() {
        return groups ;
    }


//не забудь про джэйсон игнор
}
