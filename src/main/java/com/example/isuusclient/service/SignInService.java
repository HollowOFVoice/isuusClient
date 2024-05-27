package com.example.isuusclient.service;

import com.example.isuusclient.MainApplication;
import com.example.isuusclient.Response.DataResponse;
import com.example.isuusclient.Response.ListResponse;
import com.example.isuusclient.controller.MainController;
import com.example.isuusclient.entity.UsersEntity;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
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
    public static UsersEntity rol = new UsersEntity();


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




    public void add(UsersEntity data){
        String temp = http.post(prop.getSaveUser(), service.getJson(data));
        DataResponse<UsersEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.add(respose.getData());

        }else{

            throw new RuntimeException(respose.getMessage());
        }
    }



    public UsersEntity checkUserData(UsersEntity data) throws IOException {
        //String url = "http://localhost:2825/api/v1/user?username=" + username + "&password=" + password;
        String temp = http.get(prop.getCheckUser() + data.getUsername()+"&password="+data.getPassword());
        DataResponse<UsersEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            //alertService.showResUserCheck(respose.getData(),"Найдено совпадение по вашим данным!");
            MainApplication.start2("Главная");
            this.data.addAll(respose.getData());


            
        }else{

            alertService.dinfoundUser();

        }
return data;
    }



    public UsersEntity checkUser(UsersEntity data) throws IOException {
        //String url = "http://localhost:2825/api/v1/user?username=" + username + "&password=" + password;
        String temp = http.get(prop.getCheckUser() + data.getUsername()+"&password="+data.getPassword());
        DataResponse<UsersEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            //alertService.showResUserCheck(respose.getData(),"Найдено совпадение по вашим данным!");

            this.data.addAll(respose.getData());



        }else{



        }
return data;
    }




}
