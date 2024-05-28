package com.example.isuusclient.controller;

import com.example.isuusclient.MainApplication;
import com.example.isuusclient.entity.GroupsEntity;
import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.GroupService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import static com.example.isuusclient.MainApplication.userAdmin;
import static com.example.isuusclient.MainApplication.userInf;

@Getter
@Setter
public class GroupListController {

    private final ErrorAlertService alertService = new ErrorAlertService();
    private final GroupService service = new GroupService();
    private boolean addFlag = true;
    @FXML
    private TableColumn<GroupsEntity, String> groupColumn;

    @FXML
    private TableView<GroupsEntity> groupTable;
    @FXML
    private Button backButton;

    @FXML
    private Button addButton;


    @FXML
    private Button deleteButton;

    @FXML
    private void addGroup(){
        MainApplication.showDialog("add-group-view.fxml","Добавить группу");
    }
    @FXML
    void assessmenList(ActionEvent event){
        try {
            MainApplication.showDialog2("assessmen-list.fxml", "Список специальностей");
        }catch (Exception e){alertService.didntStart(e);}
        }

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

    private GroupsEntity getSelectionElement() {
        GroupsEntity temp = groupTable.getSelectionModel().getSelectedItem();
        return temp;
    }


    @FXML
    private void initialize(){

        if (userAdmin.equals(userInf)) {
            addButton.setVisible(true);
            deleteButton.setVisible(true);
        } else {
            addButton.setVisible(false);
            deleteButton.setVisible(false);
        }

        service.getAll();
        groupColumn.setCellValueFactory(new PropertyValueFactory<GroupsEntity, String>("groups"));
        groupTable.setItems(service.getData());

    }
}
