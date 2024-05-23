package com.example.isuusclient.controller;

import com.example.isuusclient.MainApplication;
import com.example.isuusclient.entity.StudentsEntity;


import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.HttpService;
import com.example.isuusclient.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class MainController {



    @FXML
    private TableColumn<StudentsEntity, String> groupColumn;

    @FXML
    private TableColumn<StudentsEntity, String> lastNameTable;

    @FXML
    private TableColumn<StudentsEntity,String> nameTable;

    @FXML
    private TableColumn<StudentsEntity, String> recbookTable;

    @FXML
    private TableColumn<StudentsEntity, String> specialTable;

    @FXML
    private TableView<StudentsEntity> studentTable;

    @FXML
    private TableColumn<StudentsEntity, String> surnameTable;
   StudentService service = new StudentService();
    private final ErrorAlertService alertService = new ErrorAlertService();

//    @FXML
//    void deleteBookAction(ActionEvent event) {
//        HttpService service = new HttpService();
//        System.out.println(service.get("http://localhost:2825/api/v1/student/all"));
//    }
@FXML
void addOrChangeAssessmen(ActionEvent event){
    MainApplication.showDialog("ad-assessmen-view.fxml", "Добавить/изменить Оценку");
}


    private Optional<StudentsEntity> students = Optional.empty();
    public void setStudent(Optional<StudentsEntity> students){
        try{
            this.students = students;
            if (students.isPresent()) {
                if (students.get().getId() != null)
                    service.update(students.get(), studentTable.getSelectionModel().getSelectedItem());
                else service.add(students.get());
            }
        }catch (Exception e){
            alertService.addVoid(e);
        }

    }

@FXML
    void addStudent(ActionEvent event){
    Optional<StudentsEntity> students = Optional.empty();
    MainApplication.showBookDialog(students);
}
    private StudentsEntity getSelectionElement(){
        StudentsEntity temp = studentTable.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void changeStudent(ActionEvent event){
        Optional<StudentsEntity> students = Optional.of(getSelectionElement());
        MainApplication.showBookDialog(students);
    }

    @FXML
    void GroupList(ActionEvent event){
        MainApplication.showDialog2("group-list.fxml", "Список Групп");
    }

    @FXML
    void lessonList(ActionEvent event){
        MainApplication.showDialog2("lesson-list.fxml", "Список изучаемых предметов");
    }

    @FXML
    void SpecialList(ActionEvent event){
        MainApplication.showDialog2("special-list.fxml", "Список специальностей");
    }

    @FXML
    private void initialize(){
        //получаем список с сервера
        service.getAll();
        //связываем поля таблицы со столбцами
        nameTable.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("name"));
        surnameTable.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("surname"));
        lastNameTable.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("lastname"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("group"));
        specialTable.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("special"));
        recbookTable.setCellValueFactory(new PropertyValueFactory<StudentsEntity, String>("recordBook"));
       studentTable.setItems(service.getData());
    }

}