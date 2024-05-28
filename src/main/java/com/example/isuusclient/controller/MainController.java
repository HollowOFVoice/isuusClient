package com.example.isuusclient.controller;

import com.example.isuusclient.MainApplication;
import com.example.isuusclient.entity.StudentsEntity;


import com.example.isuusclient.entity.UsersEntity;
import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.HttpService;
import com.example.isuusclient.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Optional;

import static com.example.isuusclient.MainApplication.sign;
import static com.example.isuusclient.MainApplication.signserv;

@Getter
@Setter
public class MainController {

    @FXML
    private Button addButton;

    @FXML
    private Button changeButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<StudentsEntity, String> historyColumn;

    @FXML
    private TableView<StudentsEntity> historyTable;

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
    private TextField searchField;
    @FXML
    private TableColumn<StudentsEntity, String> surnameTable;
   StudentService service = new StudentService();
    private final ErrorAlertService alertService = new ErrorAlertService();

    @FXML
        void searchByRecBook(ActionEvent event) {
        try {
            StudentsEntity studentsEntity = new StudentsEntity();
            studentsEntity.setRecordBook(Long.valueOf(searchField.getText()));
            service.findByRec(studentsEntity);
            MainApplication.showDialog("main-view.fxml", "Главная");
        }catch (Exception e){
           alertService.didntStart(e);
        }
    }

    @FXML
    void delete(ActionEvent event) {
        try {
            service.delete(getSelectionElement());
        } catch (Exception e) {
            alertService.deleteVoid(e);

        }
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
        try {
            Optional<StudentsEntity> students = Optional.empty();
            MainApplication.showBookDialog(students);
        }catch (Exception e){alertService.didntStart(e);}
}
    private StudentsEntity getSelectionElement(){
        StudentsEntity temp = studentTable.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void changeStudent(ActionEvent event){
        try{
        Optional<StudentsEntity> students = Optional.of(getSelectionElement());
        MainApplication.showBookDialog(students);}catch (Exception e){alertService.didntStart(e);}
    }

    @FXML
    void GroupList(ActionEvent event){
        try {
            MainApplication.showDialog2("group-list.fxml", "Список Групп");
        }catch (Exception e){alertService.didntStart(e);}
    }

    @FXML
    void lessonList(ActionEvent event){
        try {
            MainApplication.showDialog2("lesson-list.fxml", "Список изучаемых предметов");
        } catch (Exception e){alertService.didntStart(e);}
    }

    @FXML
    void SpecialList(ActionEvent event){
        try {
            MainApplication.showDialog2("special-list.fxml", "Список специальностей");
        }catch (Exception e){alertService.didntStart(e);}
    }
@FXML
    protected void onHellpButtonClick() throws IOException {
        Runtime.getRuntime().exec("hh.exe C:\\Users\\User\\Desktop\\primeMOY\\help.chm");
}


    @SneakyThrows
    @FXML
    private void initialize(){

                UsersEntity usersEntity = new UsersEntity();
        UsersEntity usersEntity1 = new UsersEntity();



        usersEntity.getUsername();
        usersEntity.getPassword();
        signserv.checkUserData(usersEntity);
        System.out.println(signserv.getData());
//        if (usersEntity.equals(signserv.getData())){
//            addButton.setVisible(true);
//        }else addButton.setVisible(false);



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