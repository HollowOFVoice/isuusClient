package com.example.isuusclient.controller;

import com.example.isuusclient.entity.AssessmenEntity;
import com.example.isuusclient.entity.GroupsEntity;
import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.service.SpecialService;
import com.example.isuusclient.service.StudentService;
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
    StudentService service = new StudentService();
    SpecialService specialService = new SpecialService();

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


//    @FXML
//    private void initialize(){
//        authorService.getAll();
//        publisherService.getAll();
//        genreService.getAll();
//        ComboboxAuthor.setItems(authorService.getData());
//        ComboboxGenre.setItems(genreService.getData());
//        ComboboxPublisher.setItems(publisherService.getData());
//    }



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
      //  alertService.addVoid(e);
    }
        Stage stage = (Stage) saveStudent.getScene().getWindow();
        stage.close();
    }


    @FXML
    void cancel(ActionEvent event) {
       Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }


}






