package com.example.isuusclient.service;

import com.example.isuusclient.Response.DataResponse;
import com.example.isuusclient.entity.SpecialsEntity;
import com.example.isuusclient.entity.UsersEntity;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;

public class SignInService {

    @Getter
    private ObservableList<UsersEntity> data = FXCollections.observableArrayList();
    private  final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<UsersEntity>>() {
    }.getType();
    private Type listType = new TypeToken<DataResponse<UsersEntity>>() {
    }.getType();


}
