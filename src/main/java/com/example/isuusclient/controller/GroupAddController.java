package com.example.isuusclient.controller;


import com.example.isuusclient.entity.GroupsEntity;

import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.GroupService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GroupAddController {

    private final ErrorAlertService alertService = new ErrorAlertService();
    private final GroupService service = new GroupService();
    private boolean addFlag = true;
    @FXML
    private ListView<GroupsEntity> dataList;

    @FXML
    private Button saveGroup;

    @FXML
    private TextField textGroup;

    @FXML
    private void initialize() {
        service.getAll();
        dataList.setItems(service.getData());

    }


    @FXML
    void addGroup(ActionEvent event) {

        try {
            GroupsEntity groups = new GroupsEntity();
            groups.setGroups(textGroup.getText());
            if (addFlag) {
                service.add(groups);
            } else {
                groups.setId(getSelectionElement().getId());
                service.update(groups, getSelectionElement());
            }
            textGroup.clear();
        } catch (Exception e) {
            alertService.addVoid(e);
        }
        Stage stage = (Stage) saveGroup.getScene().getWindow();
        stage.close();
        saveGroup.setText("Добавить");
    }


    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                addFlag = false;
                GroupsEntity temp = getSelectionElement();
                textGroup.setText(temp.getGroups());
                saveGroup.setText("Изменить");
            }
        }
    }

    private GroupsEntity getSelectionElement() {
        GroupsEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void cancel(ActionEvent event) {
        addFlag = true;
        dataList.editableProperty().setValue(false);
        textGroup.clear();
        saveGroup.setText("Добавить");

    }


}
