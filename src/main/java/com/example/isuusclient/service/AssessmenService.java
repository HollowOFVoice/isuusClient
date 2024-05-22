package com.example.isuusclient.service;

import com.example.isuusclient.Response.BaseResponse;
import com.example.isuusclient.Response.DataResponse;
import com.example.isuusclient.Response.ListResponse;
import com.example.isuusclient.entity.AssessmenEntity;
import com.example.isuusclient.entity.StudentsEntity;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;

public class    AssessmenService {
    @Getter
    private ObservableList<AssessmenEntity> data = FXCollections.observableArrayList();
    private  final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<AssessmenEntity>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<AssessmenEntity>>() {
    }.getType();


    public void getAll(){
        ListResponse<AssessmenEntity> data=new ListResponse<>();
        data = service.getObject(http.get(prop.getAllAssessmen()),listType);
        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }


    public void add(AssessmenEntity data){
        String temp = http.post(prop.getSaveAssessmen(), service.getJson(data));
        DataResponse<AssessmenEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.add(respose.getData());

        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }

    public void update(AssessmenEntity after, AssessmenEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateAssessmen(), service.getJson(after));
        DataResponse<AssessmenEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }

    public void delete(AssessmenEntity data){
        String temp = http.delete(prop.getDeleteAssessmen(), data.getId());
        BaseResponse response = service.getObject(temp,BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }



}
