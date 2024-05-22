package com.example.isuusclient.controller;

import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.service.HttpService;
import com.example.isuusclient.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MainController {
    @FXML
    private TableColumn<StudentsEntity, String> groupColumn;

    @FXML
    private TableColumn<StudentsEntity, String> lastNameTable;

    @FXML
    private TableColumn<StudentsEntity,String> nameTable;

    @FXML
    private TableColumn<StudentsEntity, Integer> recbookTable;

    @FXML
    private TableColumn<StudentsEntity, String> specialTable;

    @FXML
    private TableView<StudentsEntity> studentTable;

    @FXML
    private TableColumn<StudentsEntity, String> surnameTable;

    StudentService service = new StudentService();

    @FXML
    void deleteBookAction(ActionEvent event) {
        HttpService service = new HttpService();
        System.out.println(service.get("http://localhost:2825/api/v1/student/all"));
    }

    @FXML
    private void initialize(){
        //получаем все книги с сервера
        service.getAll();
        //связываем поля таблицы со столбцами
        nameTable.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("name"));
        surnameTable.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("surname"));
        lastNameTable.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("lastname"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("group"));
        specialTable.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("special"));
        recbookTable.setCellValueFactory(new PropertyValueFactory<StudentsEntity, Integer>("recbook"));
        studentTable.setItems(service.getData());
    }

}