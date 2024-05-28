package com.example.isuusclient.controller;

import com.example.isuusclient.MainApplication;
import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.entity.UsersEntity;
import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.SignInService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.isuusclient.MainApplication.*;
//import static com.example.isuusclient.MainApplication.signInController;

public class SignInController {
ErrorAlertService alertService = new ErrorAlertService();
    SignInService service = new SignInService();

    @FXML
    private Button enterButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    void enter(ActionEvent event) throws IOException {
try {
    userAdmin.setUsername("admin");
    userAdmin.setPassword("12345");
    //UsersEntity usersEntity = new UsersEntity();
    userInf.setUsername(loginField.getText());
    userInf.setPassword(passwordField.getText());

    signserv.checkUserData(userInf);




}catch (Exception e){
    alertService.didntStart(e);
}
        Stage stage = (Stage) enterButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registr(ActionEvent event) {
        try {
            MainApplication.showDialog("add-user-view.fxml", "Регистрация нового пользователя");
        }catch (Exception e){
            alertService.didntStart(e);
        }
        }

}
