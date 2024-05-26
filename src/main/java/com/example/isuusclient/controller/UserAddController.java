package com.example.isuusclient.controller;

import com.example.isuusclient.entity.GroupsEntity;
import com.example.isuusclient.entity.UsersEntity;
import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.SignInService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserAddController {


    @FXML
    private Button cancelButton;
    @FXML
    private Button saveUser;
    @FXML
    private TextField passwordField;

    @FXML
    private TextField userNameField;

    private final ErrorAlertService alertService = new ErrorAlertService();
    SignInService service = new SignInService();

    @FXML
    void addUser(ActionEvent event) {
        try {
            UsersEntity users = new UsersEntity();
            users.setUsername(userNameField.getText());
            users.setPassword(passwordField.getText());
            service.add(users);
        } catch (Exception e) {
            alertService.addVoid(e);
        }
        Stage stage = (Stage) saveUser.getScene().getWindow();
        stage.close();

    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
