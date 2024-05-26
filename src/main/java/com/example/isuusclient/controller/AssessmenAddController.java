package com.example.isuusclient.controller;

import com.example.isuusclient.entity.AssessmenEntity;
import com.example.isuusclient.entity.LessonsEntity;
import com.example.isuusclient.service.AssessmenService;
import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.LessonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AssessmenAddController {
   ErrorAlertService alertService = new ErrorAlertService();
    AssessmenService service = new AssessmenService();
    LessonService lessonService = new LessonService();
    private boolean addFlag = true;
    @FXML
    private ComboBox<LessonsEntity> comBoxLesson;

    @FXML
    private ListView<AssessmenEntity> dataList;

    @FXML
    private Button saveAssess;

    @FXML
    private TextField textAssessmen;

    @FXML
    private void initialize(){
        service.getAll();
        lessonService.getAll();
        comBoxLesson.setItems(lessonService.getData());
        dataList.setItems(service.getData());

    }
    @FXML
    void addAssessmen(ActionEvent event) {
        //try {
            AssessmenEntity assessmen = new AssessmenEntity();
            assessmen.setAssessmen(textAssessmen.getText());
            assessmen.setLesson(comBoxLesson.getSelectionModel().getSelectedItem());
            if (addFlag) {
                service.add(assessmen);
            } else {
                assessmen.setId(getSelectionElement().getId());
                service.update(assessmen, getSelectionElement());
            }
            textAssessmen.clear();
      //  }catch (Exception e){
          //  alertService.addVoid(e);
    //    }
        Stage stage = (Stage) saveAssess.getScene().getWindow();
        stage.close();
        saveAssess.setText("добавить");
    }


    private AssessmenEntity getSelectionElement(){
        AssessmenEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }


    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                addFlag = false;
                AssessmenEntity temp = getSelectionElement();
                textAssessmen.setText(String.valueOf(temp.getAssessmen()));
                saveAssess.setText("Изменить");
            }
        }
    }


    @FXML
    void cancel(ActionEvent event) {
        dataList.editableProperty().setValue(false);
        textAssessmen.clear();
        saveAssess.setText("Добавить");

    }
//    @FXML
//    void delete(ActionEvent event) {
//        service.delete(getSelectionElement());
//        textAssessmen.clear();
//    }


}
