package com.example.isuusclient.controller;

import com.example.isuusclient.MainApplication;
import com.example.isuusclient.entity.GroupsEntity;
import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.service.GroupService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GroupListController {

    GroupService service = new GroupService();
    private boolean addFlag = true;
    @FXML
    private TableColumn<GroupsEntity, String> groupColumn;

    @FXML
    private TableView<GroupsEntity> groupTable;


    @FXML
    private void addGroup(){
        MainApplication.showDialog("add-group-view.fxml","Добавить группу");
    }

//    @FXML
//    private void initialize(){
//        service.getAll();
//        groupColumn.setCellValueFactory(new PropertyValueFactory< GroupsEntity, String>("group"));
//        groupTable.setItems(service.getData());
//
//    }

//    @FXML
//    private void initialize(){
//        //получаем список с сервера
//        service.getAll();
//        //связываем поля таблицы со столбцами
//
//        groupTable.setItems(service.getData());
//    }

}
