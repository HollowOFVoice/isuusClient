package com.example.isuusclient.controller;

import com.example.isuusclient.entity.AssessmenEntity;
import com.example.isuusclient.entity.GroupsEntity;
import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class StudentsAddController {
    private final ErrorAlertService alertService = new ErrorAlertService();

    StudentService studentService = new StudentService();
    SpecialService specialService = new SpecialService();
    AssessmenService assessmenService = new AssessmenService();
    GroupService groupService = new GroupService();



    private boolean addFlag = true;

    @FXML
    private TextField textRecBook;

    @FXML
    private TextField textOtchestvo;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<AssessmenEntity> comboBoxAssessmen;

    @FXML
    private ComboBox<GroupsEntity> comboBoxGroup;

    @FXML
    private TextField textFamil;

    @FXML
    private TextField textName;
    @FXML
    private Button saveStudent;


    @FXML
    private void initialize(){
        studentService.getAll();
        assessmenService.getAll();
        groupService.getAll();
        comboBoxAssessmen.setItems(assessmenService.getData());
        comboBoxGroup.setItems(groupService.getData());
    }



    @Setter
    @Getter
    private Optional<StudentsEntity> students;

    @FXML
    void addStudent(ActionEvent event) {
        try{
        StudentsEntity temp =  StudentsEntity.builder()
                .name(textName.getText())
                .surname(textFamil.getText())
                .lastname(textOtchestvo.getText())
                .recordBook(Long.valueOf(textRecBook.getText()))
                .assessmen(comboBoxAssessmen.getSelectionModel().getSelectedItem())
                .group(comboBoxGroup.getSelectionModel().getSelectedItem())
                .build();
        if (students.isEmpty()){
            students = Optional.of(temp);}
        else {
            temp.setId(students.get().getId());
        }
        System.out.println(temp);
            students = Optional.of(temp);
    }catch (Exception e){
        alertService.addVoid(e);
    }
        Stage stage = (Stage) saveStudent.getScene().getWindow();
        stage.close();
    }


    @FXML
    void cancel(ActionEvent event) {
       Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    public  void  start(){
        if (students.isPresent()){
            textName.setText(students.get().getName());
            textFamil.setText(students.get().getSurname());
            textOtchestvo.setText(students.get().getLastname());
            comboBoxGroup.setValue(students.get().getGroup());
            comboBoxAssessmen.setValue(students.get().getAssessmen());
            textRecBook.setText(String.valueOf(students.get().getRecordBook()));
        }
    }


}






