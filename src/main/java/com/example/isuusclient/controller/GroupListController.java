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
    private void initialize(){
        service.getAll();
        groupColumn.setCellValueFactory(new PropertyValueFactory<GroupsEntity, String>("groups"));
        groupTable.setItems(service.getData());

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


//    @FXML
//    private void initialize(){
//        //получаем список с сервера
//        service.getAll();
//        //связываем поля таблицы со столбцами
//
//        groupTable.setItems(service.getData());
//    }

}
