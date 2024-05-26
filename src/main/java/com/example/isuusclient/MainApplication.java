package com.example.isuusclient;

import com.example.isuusclient.controller.MainController;
import com.example.isuusclient.controller.SignInController;
import com.example.isuusclient.controller.StudentsAddController;
import com.example.isuusclient.entity.StudentsEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainApplication extends Application {
    private FXMLLoader fxmlLoader;
    private static MainController mainController;
    private static SignInController signInController;




    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user-enter-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 350);
        stage.setTitle("Вход в систему");
        stage.setScene(scene);
        signInController = fxmlLoader.getController();
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


    public static void start2(String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);

        stage.setScene(scene);
        mainController = fxmlLoader.getController();
        stage.show();
    }



    public static void showDialog(String nameView, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource(nameView));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void showDialog2(String nameView, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource(nameView));
            BorderPane page = (BorderPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void showBookDialog(Optional<StudentsEntity> students) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("add-student-view.fxml"));

            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Работа со студентом");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            StudentsAddController controller = loader.getController();

            controller.setStudents(students);
            controller.start();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
            students = controller.getStudents();
            mainController.setStudent(students);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

