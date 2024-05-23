package com.example.isuusclient.controller;

import com.example.isuusclient.MainApplication;
import com.example.isuusclient.entity.GroupsEntity;
import com.example.isuusclient.entity.LessonsEntity;
import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.GroupService;
import com.example.isuusclient.service.LessonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LessonListController {

    private final ErrorAlertService alertService = new ErrorAlertService();
    private final LessonService service = new LessonService();
    private boolean addFlag = true;


    @FXML
    private TableColumn<LessonsEntity, String> columnLesson;

    @FXML
    private TableView<LessonsEntity> tableLesson;

    @FXML
    void addLesson(ActionEvent event) {
        MainApplication.showDialog("add-lesson-view.fxml","Добавить добавить/изменить предмет");
    }





    @FXML
    void delete(ActionEvent event) {
        try {
            service.delete(getSelectionElement());
        } catch (Exception e) {
            alertService.deleteVoid(e);

        }

    }

    private LessonsEntity getSelectionElement() {
        LessonsEntity temp = tableLesson.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    private void initialize(){
        service.getAll();
        columnLesson.setCellValueFactory(new PropertyValueFactory<LessonsEntity, String>("lesson"));
        tableLesson.setItems(service.getData());

    }


}
