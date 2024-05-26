package com.example.isuusclient.controller;

import com.example.isuusclient.entity.GroupsEntity;
import com.example.isuusclient.entity.SpecialsEntity;
import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.GroupService;
import com.example.isuusclient.service.SpecialService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SpecialAddController {


    private final ErrorAlertService alertService = new ErrorAlertService();
SpecialService service = new SpecialService();
    private boolean addFlag = true;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView<SpecialsEntity> dataList;

    @FXML
    private Button saveSpecialButton;

    @FXML
    private TextField specialText;

    @FXML
    private void initialize() {
        service.getAll();
        dataList.setItems(service.getData());

    }



    // не работает добавление
    @FXML
    void addSpecial(ActionEvent event) {
        try {
            SpecialsEntity specials = new SpecialsEntity();
            specials.setSpecial(specialText.getText());
            if (addFlag) {
                service.add(specials);
            } else {
                specials.setId(getSelectionElement().getId());
                service.update(specials, getSelectionElement());
            }
            specialText.clear();

        } catch (Exception e) {
            alertService.addVoid(e);
        }
        Stage stage = (Stage) saveSpecialButton.getScene().getWindow();
        stage.close();

        saveSpecialButton.setText("Добавить");
    }

    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                addFlag = false;
                SpecialsEntity temp = getSelectionElement();
                specialText.setText(temp.getSpecial());
                saveSpecialButton.setText("Изменить");
            }
        }
    }

    private SpecialsEntity getSelectionElement() {
        SpecialsEntity temp =  dataList.getSelectionModel().getSelectedItem();
        return temp;
    }
    @FXML
    void cancel(ActionEvent event) {
        addFlag = true;
        dataList.editableProperty().setValue(false);
        specialText.clear();
        saveSpecialButton.setText("Добавить");
    }


}
