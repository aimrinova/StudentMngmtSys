package org.anubis.lectures.studentmngmtsys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void init() {
        System.out.println("Before");
    }
    // chapter1/StageStylesDemo
    Stage stage = new Stage(StageStyle.UNDECORATED);
    // or
    // stage.initStyle(StageStyle.TRANSPARENT)

    @Override
    public void start(Stage stage1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage1.setTitle("Hello!");
        stage1.setScene(scene);
        stage1.show();

        // this window doesn't block mouse and keyboard events
        Stage stage2 = new Stage();
        stage2.setTitle("I don't block anything");
        stage2.initModality(Modality.NONE);
        stage2.show();

        // this window blocks only interaction with it's owner window(stage1)
        Stage stage4 = new Stage();
        stage4.setTitle("I block only clicks to main window");
        stage4.initOwner(stage1);
        stage4.initModality(Modality.WINDOW_MODAL);
        stage4.show();

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.show();
    }

    public void stop() {
        System.out.println("After");
    }

    public static void main(String[] args) {
        launch();
    }
}