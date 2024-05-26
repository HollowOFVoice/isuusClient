package com.example.isuusclient.controller;

import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.entity.UsersEntity;
import com.example.isuusclient.service.SignInService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignInController {

    SignInService service = new SignInService();
    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    void enter(ActionEvent event) {

            UsersEntity usersEntity = new UsersEntity();
           // UsersEntity passworde = new UsersEntity();
            usersEntity.setUsername(loginField.getText());
            usersEntity.setPassword(passwordField.getText());
            service.checkUserData(usersEntity);


    }

    @FXML
    void registr(ActionEvent event) {

    }

}
