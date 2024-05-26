package com.example.isuusclient.controller;

import com.example.isuusclient.entity.AssessmenEntity;
import com.example.isuusclient.entity.GroupsEntity;
import com.example.isuusclient.entity.LessonsEntity;
import com.example.isuusclient.entity.SpecialsEntity;
import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.GroupService;
import com.example.isuusclient.service.LessonService;
import com.example.isuusclient.service.SpecialService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LessonAddController {

    private final LessonService service = new LessonService();
    private final SpecialService specialService = new SpecialService();
    private final ErrorAlertService alertService = new ErrorAlertService();
    private boolean addFlag = true;

    @FXML
    private ComboBox<SpecialsEntity> comboboxSpecial;

    @FXML
    private ListView<LessonsEntity> dataList;
    @FXML
    private TextField textLesson;
    @FXML
    private Button saveLesson;


    @FXML
    private void initialize() {
        service.getAll();
        specialService.getAll();
        dataList.setItems(service.getData());
        comboboxSpecial.setItems(specialService.getData());
    }

    @FXML
    void addLesson(ActionEvent event) {


        try {
            LessonsEntity lessons = new LessonsEntity();
            lessons.setLesson(textLesson.getText());
            lessons.setSpecial(comboboxSpecial.getSelectionModel().getSelectedItem());
            if (addFlag) {
                service.add(lessons);
            } else {
                lessons.setId(getSelectionElement().getId());
                service.update(lessons, getSelectionElement());
            }
            textLesson.clear();
        }catch (Exception e){
            alertService.addVoid(e);
        }
        Stage stage = (Stage) saveLesson.getScene().getWindow();
        stage.close();
    }
    private LessonsEntity getSelectionElement() {
        LessonsEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }


    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                addFlag = false;
                LessonsEntity temp = getSelectionElement();
                textLesson.setText(temp.getLesson());
                saveLesson.setText("Изменить");
            }
        }
    }



    @FXML
    void cancel(ActionEvent event) {
        dataList.editableProperty().setValue(false);
        textLesson.clear();
        saveLesson.setText("Добавить");
    }
}