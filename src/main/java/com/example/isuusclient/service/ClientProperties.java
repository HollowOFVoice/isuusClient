package com.example.isuusclient.service;


import com.example.isuusclient.MainApplication;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ClientProperties {
    private  final Properties properties = new Properties();

    private String allStudent;
    private String fineByRec;
    private String saveStudent;
    private String updateStudent;

    private String allAssessmen;
    private String saveAssessmen;
    private String updateAssessmen;
    private String allGroup;

    private String saveGroup;
    private String updateGroup;
    private String allLessons;
    private String saveLessons;
    private String updateLessons;

    private String deleteStudent;
    private String deleteAssessmen;
    private String deleteGroup;
    private String deleteLesson;


    private String allSpecial;
    private String saveSpecial;
    private String updateSpecial;
    private String deleteSpecial;

    private String saveUser;

    public ClientProperties() {
        try (InputStream input = MainApplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            System.out.println(input);
            properties.load(input);//загрузка свойства из файла
            allStudent = properties.getProperty("student.getAll");
            fineByRec = properties.getProperty("student.fineById");
            saveStudent = properties.getProperty("student.save");
            updateStudent = properties.getProperty("student.update");

            allAssessmen = properties.getProperty("assessmen.getAll");
            saveAssessmen = properties.getProperty("assessmen.save");
            updateAssessmen = properties.getProperty("assessmen.update");
            allGroup = properties.getProperty("group.getAll");
            saveGroup= properties.getProperty("group.save");
            updateGroup = properties.getProperty("group.update");
            allSpecial = properties.getProperty("special.getAll");
            saveSpecial = properties.getProperty("special.save");
            updateSpecial = properties.getProperty("special.update");
            deleteStudent = properties.getProperty("student.del");
            deleteAssessmen = properties.getProperty("assessmen.del");
            deleteGroup = properties.getProperty("group.del");
            deleteSpecial = properties.getProperty("special.del");
    allLessons = properties.getProperty("lesson.getAll");
    saveLessons = properties.getProperty("lesson.save");
    updateLessons = properties.getProperty("lesson.update");
    deleteLesson = properties.getProperty("lesson.del");
saveUser = properties.getProperty("user.save");
        } catch (IOException e) {
            e.printStackTrace();//обробатываеь исключение в слуучае ошибки
        }

    }
}
