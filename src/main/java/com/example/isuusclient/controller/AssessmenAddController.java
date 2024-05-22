package com.example.isuusclient.controller;

import com.example.isuusclient.entity.AssessmenEntity;
import com.example.isuusclient.service.AssessmenService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AssessmenAddController {
    AssessmenService service = new AssessmenService();
    private boolean addFlag = true;
    @FXML
    private ComboBox<AssessmenEntity> comBoxLesson;

    @FXML
    private ListView<AssessmenEntity> dataList;

    @FXML
    private TextField textAssessmen;

    @FXML
    private void initialize(){
        service.getAll();
        dataList.setItems(service.getData());

    }
    @FXML
    void addAssessmen(ActionEvent event) {



    }


    private AssessmenEntity getSelectionElement(){
        AssessmenEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void cancel(ActionEvent event) {

    }


}
