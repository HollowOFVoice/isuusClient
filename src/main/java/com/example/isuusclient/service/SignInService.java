package com.example.isuusclient.service;

import com.example.isuusclient.MainApplication;
import com.example.isuusclient.Response.DataResponse;
import com.example.isuusclient.Response.ListResponse;
import com.example.isuusclient.controller.MainController;
import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.entity.UsersEntity;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.lang.reflect.Type;

public class SignInService {

    @Getter
    private ObservableList<UsersEntity> data = FXCollections.observableArrayList();
    private final ErrorAlertService alertService = new ErrorAlertService();
    private  final HttpService http = new HttpService();
    JsonService service = new JsonService();
    private FXMLLoader fxmlLoader;
    private static MainController mainController;


    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<UsersEntity>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<UsersEntity>>() {
    }.getType();


//    public void userfindForCheck(UsersEntity data){
//        String temp = http.get(prop.getCheckUser() + data.getUsername()+data.getPassword());
//        DataResponse<UsersEntity> respose = service.getObject(temp, dataType);
//        if (respose.isSuccess()){
//
//            alertService.dinfoundUser();
//        }else{
//
//            alertService.showResUserCheck(respose.getData(),"Найдено совпадение по вашим данным!");
//
//        }
//    }






    public void checkUserData(UsersEntity data) {
        //String url = "http://localhost:2825/api/v1/user?username=" + username + "&password=" + password;
        String temp = http.get(prop.getCheckUser() + data.getUsername()+"&password="+data.getPassword());
        DataResponse<UsersEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            //alertService.showResUserCheck(respose.getData(),"Найдено совпадение по вашим данным!");
            MainApplication.showDialog2("main-view.fxml","Главная");

        }else{

            alertService.dinfoundUser();

        }
    }

}
