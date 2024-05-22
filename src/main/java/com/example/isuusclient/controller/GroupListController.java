package com.example.isuusclient.controller;

import com.example.isuusclient.entity.GroupsEntity;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GroupListController {

    @FXML
    private TableColumn<GroupsEntity, String> groupColumn;

    @FXML
    private TableView<GroupsEntity> groupTable;


}
