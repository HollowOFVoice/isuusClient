package com.example.isuusclient.controller;

import com.example.isuusclient.MainApplication;
import com.example.isuusclient.entity.AssessmenEntity;
import com.example.isuusclient.entity.LessonsEntity;
import com.example.isuusclient.entity.SpecialsEntity;
import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AssessmenListController {
    private final ErrorAlertService alertService = new ErrorAlertService();
    private final AssessmenService service = new AssessmenService();
    //private final SpecialService specialService = new SpecialService();
    private final LessonService lessonService = new LessonService();
    private boolean addFlag = true;


    @FXML
    private TableColumn<AssessmenEntity, String> assessColumn;

    @FXML
    private TableView<AssessmenEntity> assessmenTable;

    @FXML
    private TableColumn<LessonsEntity, String> lessonTable;

    @FXML
    private TableColumn<SpecialsEntity, String> specialColumn;
    @FXML
    private Button backButton;


    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void delete(ActionEvent event) {
        try {
            service.delete(getSelectionElement());
        } catch (Exception e) {
            alertService.deleteVoid(e);

        }

    }


    private AssessmenEntity getSelectionElement() {
        AssessmenEntity temp = assessmenTable.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void addAssessmen(ActionEvent event) {
        MainApplication.showDialog("ad-assessmen-view.fxml","Добавить добавить/изменить оценку");
    }

    @FXML
    private void initialize(){
        service.getAll();
        lessonService.getAll();

        assessColumn.setCellValueFactory(new PropertyValueFactory<AssessmenEntity, String>("assessmen"));

        lessonTable.setCellValueFactory(new PropertyValueFactory<LessonsEntity, String>("lesson"));
        assessmenTable.setItems(service.getData());

    }



}
