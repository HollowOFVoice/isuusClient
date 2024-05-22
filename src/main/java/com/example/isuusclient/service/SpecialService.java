package com.example.isuusclient.service;

import com.example.isuusclient.Response.BaseResponse;
import com.example.isuusclient.Response.DataResponse;
import com.example.isuusclient.Response.ListResponse;
import com.example.isuusclient.entity.LessonsEntity;
import com.example.isuusclient.entity.SpecialsEntity;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;

public class SpecialService {

    @Getter
    private ObservableList<SpecialsEntity> data = FXCollections.observableArrayList();
    private  final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<SpecialsEntity>>() {
    }.getType();
    private Type listType = new TypeToken<DataResponse<SpecialsEntity>>() {
    }.getType();


    public void getAll(){
        ListResponse<SpecialsEntity> data=new ListResponse<>();
        data = service.getObject(http.get(prop.getAllSpecial()),listType);
        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }


    public void add(SpecialsEntity data){
        String temp = http.post(prop.getSaveSpecial(), service.getJson(data));
        DataResponse<SpecialsEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.add(respose.getData());

        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }

    public void update(SpecialsEntity after, SpecialsEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateSpecial(), service.getJson(after));
        DataResponse<SpecialsEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }

    public void delete(SpecialsEntity data){
        String temp = http.delete(prop.getDeleteSpecial(), data.getId());
        BaseResponse response = service.getObject(temp,BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

}
