package com.example.isuusclient.service;

import com.example.isuusclient.Response.BaseResponse;
import com.example.isuusclient.Response.DataResponse;
import com.example.isuusclient.Response.ListResponse;
import com.example.isuusclient.entity.StudentsEntity;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;



public class StudentService {
    @Getter
    private ObservableList<StudentsEntity> data = FXCollections.observableArrayList();
     ErrorAlertService alertService = new ErrorAlertService();
    private  final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<StudentsEntity>>(){

    }.getType(); //фиксируем тип DataResponce<BookEntity>

    private Type listType = new TypeToken<ListResponse<StudentsEntity>>(){

    }.getType(); //фиксируем тип DataResponce<BookEntity>


    public void getAll(){
        ListResponse<StudentsEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getAllStudent()),listType);
        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }


    public void add(StudentsEntity data){
        String temp = http.post(prop.getSaveStudent(), service.getJson(data));
        DataResponse<StudentsEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.add(respose.getData());

        }else{

            throw new RuntimeException(respose.getMessage());
        }
    }

    public void update(StudentsEntity after, StudentsEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateStudent(), service.getJson(after));
        DataResponse<StudentsEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }

    public void delete(StudentsEntity data){
        String temp = http.delete(prop.getDeleteStudent(), data.getId());
        BaseResponse response = service.getObject(temp,BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }






    public void findByRec(StudentsEntity data){
        String temp = http.get(prop.getFineByRec() + data.getRecordBook());
        DataResponse<StudentsEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            alertService.showRes(respose.getData(),"Найдено совпадение по вашим данным!");

        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }
    }

//        public void findByRec(StudentsEntity data){
//           ListResponse<StudentsEntity> data = service.getObject(http.get(prop.getFineByRec()),listType );
//        if (data.isSuccess()){
//            this.data.addAll(data.getData());
//this.data.forEach(System.out::println);
//        }else{
//            throw new RuntimeException(data.getMessage());
//        }
//    }


