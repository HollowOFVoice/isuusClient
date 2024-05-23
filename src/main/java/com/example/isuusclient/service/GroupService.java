package com.example.isuusclient.service;

import com.example.isuusclient.Response.BaseResponse;
import com.example.isuusclient.Response.DataResponse;
import com.example.isuusclient.Response.ListResponse;
import com.example.isuusclient.entity.AssessmenEntity;
import com.example.isuusclient.entity.GroupsEntity;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;

public class GroupService {

    @Getter
    private ObservableList<GroupsEntity> data = FXCollections.observableArrayList();
    private  final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<GroupsEntity>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<GroupsEntity>>() {
    }.getType();

    public void getAll(){
        ListResponse<GroupsEntity> data=new ListResponse<>();
        data = service.getObject(http.get(prop.getAllGroup()),listType);
        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }


    public void add(GroupsEntity data){
        String temp = http.post(prop.getSaveGroup(), service.getJson(data));
        DataResponse<GroupsEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.add(respose.getData());

        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }

    public void update(GroupsEntity after, GroupsEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateGroup(), service.getJson(after));
        DataResponse<GroupsEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }

    public void delete(GroupsEntity data){
        String temp = http.delete(prop.getDeleteGroup(), data.getId());
        BaseResponse response = service.getObject(temp,BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }



}
