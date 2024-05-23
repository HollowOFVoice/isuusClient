package com.example.isuusclient.controller;

import com.example.isuusclient.entity.AssessmenEntity;
import com.example.isuusclient.entity.GroupsEntity;
import com.example.isuusclient.service.AssessmenService;
import com.example.isuusclient.service.GroupService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GroupAddController {
    GroupService service = new GroupService();
    private boolean addFlag = true;
    @FXML
    private ListView<GroupsEntity> dataList;

    @FXML
    private Button saveGroup;

    @FXML
    private TextField textGroup;

//    @FXML
//    private void initialize(){
//        service.getAll();
//        dataList.setItems(service.getData());
//
//    }

    @FXML
    void addGroup(ActionEvent event) {
        try {

            GroupsEntity group = new GroupsEntity();
            group.setGroups((textGroup.getText()));
            if (addFlag) {
                service.add(group);
            } else {
                group.setId(getSelectionElement().getId());
                service.update(group, getSelectionElement());
            }
            textGroup.clear();
            Stage stage = (Stage) saveGroup.getScene().getWindow();
            stage.close();
            saveGroup.setText("Добавить");
        } catch (Exception e) {
//            alertService.addVoid(e);
        }
    }

    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                addFlag = false;
                GroupsEntity temp = getSelectionElement();
                textGroup.setText(String.valueOf(temp.getGroups()));
                saveGroup.setText("Изменить");
            }
        }
    }

    private GroupsEntity getSelectionElement(){
        GroupsEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void cancel(ActionEvent event) {
        dataList.editableProperty().setValue(false);
        textGroup.clear();
        saveGroup.setText("Добавить");

    }

}
