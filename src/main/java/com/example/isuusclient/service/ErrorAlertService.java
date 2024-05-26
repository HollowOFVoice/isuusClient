package com.example.isuusclient.service;

import com.example.isuusclient.entity.StudentsEntity;
import com.example.isuusclient.entity.UsersEntity;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorAlertService extends Application {

    public void showError(Exception e, String whatMistakeStr) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ПОдстава ╰（‵□′）╯ !");
        alert.setHeaderText(whatMistakeStr);

        VBox dialogPaneContent = new VBox();

        Label label = new Label("Ошибки:");

        String stackTrace = this.getStackTrace(e);
        TextArea textArea = new TextArea();
        textArea.setText(stackTrace);

        dialogPaneContent.getChildren().addAll(label, textArea);

        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }

    public void showRes(StudentsEntity data, String whatMistakeStr) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Поиск");
        alert.setHeaderText(whatMistakeStr);

        VBox dialogPaneContent = new VBox();

        Label label = new Label("Найден следующий студент:");


        TextArea textArea = new TextArea();
        textArea.setText(String.valueOf(data));

        dialogPaneContent.getChildren().addAll(label, textArea);

        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }

    public void showResUserCheck(UsersEntity data, String whatMistakeStr) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Поиск");
        alert.setHeaderText(whatMistakeStr);

        VBox dialogPaneContent = new VBox();

        Label label = new Label("Найден следующий студент:");


        TextArea textArea = new TextArea();
        textArea.setText(String.valueOf(data));

        dialogPaneContent.getChildren().addAll(label, textArea);

        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }



    public void didntStart(Exception e) {
        String whatMistakeStr = "Проверьте, возможноо провайдер отрезал кабель, или кто-то не тыкнул на кнопку запуска сервера!";
        showError(e, whatMistakeStr);
    }
    public void dinStart() {
        String whatMistakeStr = "Проверьте, возможноо провайдер отрезал кабель, или кто-то не тыкнул на кнопку запуска сервера!";
        showAlertWithHeaderText(whatMistakeStr);
    }

    public void dinfound(Exception e) {
        String whatMistakeStr = "Студента с таким номером зачетной книжки не существует! Попробуйте проверить, верность введенных данных";
        showAlertWithHeaderText222(whatMistakeStr);
    }

    public void dinfoundUser() {
        String whatMistakeStr = "Пользователья с такими данными не существует!";
        showAlertWithHeaderText222(whatMistakeStr);
    }

//    public void found(ObservableList<StudentsEntity> data) {
//        String whatMistakeStr = "Результат поиска: ( "+data+" )                                                            ";
//        showAlertWithHeaderText222(whatMistakeStr);
//    }


    public void found(StudentsEntity students) {
        String whatMistakeStr = "Результат поиска: ( "+students+" )                                                            ";
        showAlertWithHeaderText222(whatMistakeStr);
    }

    public void addVoid(Exception e) {
        String whatMistakeStr = "Вы пытаетесь добавить полное ничего или же не коректно вввели данные! - Возможен ввод только кирилицы начиная с большой буквы!"
                ;
        showError(e, whatMistakeStr);
    }

    public void deleteVoid(Exception e) {
        String whatMistakeStr = "Вы пытаетесь удалить полное ничего либо же сервер устал ? - Выберете обьет для удалени, лиюо проверьте сервер на вкл!";
        showError(e, whatMistakeStr);
    }

    private void showAlertWithHeaderText(String whatMistakeStr) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        alert.setX(bounds.getMaxX() - 800);
        alert.setY(bounds.getMaxY() - 650);
        alert.setTitle("Atempshon!");
        alert.setHeaderText("Внимание!");
        alert.setContentText(whatMistakeStr);

        alert.showAndWait();
    }






    private void showAlertWithHeaderText222(String whatMistakeStr) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        alert.setX(bounds.getMaxX() - 800);
        alert.setY(bounds.getMaxY() - 650);
        alert.setTitle("Поиск");
        alert.setHeaderText("Результаты поиска: ");
        alert.setContentText(whatMistakeStr);

        alert.showAndWait();
    }


    public void incorrectInput() {
        String whatMistakeStr = "\nВ полях со звездочкой данные должны начинаться с большой буквы, воспринимается только кириллица, English nein! Цифры nein!";
        showAlertWithHeaderText(whatMistakeStr);
    }

    @Override
    public void start(Stage stage) {
        Button button1 = new Button("показать ошибку");

        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

            }
        });

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        root.getChildren().addAll(button1);

        Scene scene = new Scene(root, 450, 250);
        stage.setTitle("JavaFX Error Alert (o7planning.org)");
        stage.setScene(scene);

        stage.show();

    }

    public String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = sw.toString();
        return s;
    }
}