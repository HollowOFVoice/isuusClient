package com.example.isuusclient.controller;

import com.example.isuusclient.MainApplication;

import com.example.isuusclient.entity.SpecialsEntity;
import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.service.ErrorAlertService;

import com.example.isuusclient.service.SpecialService;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Optional;

import static com.example.isuusclient.MainApplication.userAdmin;
import static com.example.isuusclient.MainApplication.userInf;

public class SpecialListController {

    private final ErrorAlertService alertService = new ErrorAlertService();
    private final SpecialService service = new SpecialService();
    private boolean addFlag = true;
    @FXML
    private Button backButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<SpecialsEntity, String> specialColumn;

    @FXML
    private TableView<SpecialsEntity> specialTable;

    //скорее всего, чтобы после добавления таблица автоматичеки обновлялась,
    //Нужно делать через обсервабл лист

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
        specialColumn.setCellValueFactory(new PropertyValueFactory<SpecialsEntity, String>("special"));
        specialTable.setItems(service.getData());

    }


    @FXML
    void addSpecial(ActionEvent event) {
        try {
            MainApplication.showDialog("add-special-view.fxml", "Добавить специальность");
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

    private SpecialsEntity getSelectionElement() {
        SpecialsEntity temp = specialTable.getSelectionModel().getSelectedItem();
        return temp;
    }

}
