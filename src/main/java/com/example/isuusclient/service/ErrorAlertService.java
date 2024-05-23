package com.example.isuusclient.service;

import javafx.application.Application;
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

    public void didntStart(Exception e) {
        String whatMistakeStr = "Проверьте, возможноо провайдер отрезал кабель, или кто-то не тыкнул на кнопку запуска сервера!";
        showError(e, whatMistakeStr);
    }
    public void dinStart() {
        String whatMistakeStr = "Проверьте, возможноо провайдер отрезал кабель, или кто-то не тыкнул на кнопку запуска сервера!";
        showAlertWithHeaderText(whatMistakeStr);
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
        alert.setHeaderText("Смотри что пишешь!");
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