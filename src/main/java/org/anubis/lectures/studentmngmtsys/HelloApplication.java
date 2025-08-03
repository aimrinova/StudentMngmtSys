// --- src/main/java/org/anubis/lectures/studentmngmtsys/HelloApplication.java ---
package org.anubis.lectures.studentmngmtsys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.util.Arrays;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void init() {
        // Preload resources, validate environment
        System.out.println("[INIT] Pre-initialization");
        Map<String, String> named = getParameters().getNamed();
        if (named.containsKey("config")) {
            System.out.println("Loading config: " + named.get("config"));
            // TODO: load configuration file
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/anubis/lectures/studentmngmtsys/hello-view.fxml"));
            if (loader.getLocation() == null) {
                throw new IllegalStateException("Cannot find FXML 'hello-view.fxml'");
            }
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Startup Error");
            alert.setHeaderText("Unable to load UI");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            Platform.exit();
        }
    }

    private boolean handleCliMode(java.util.List<String> args) {
        boolean handled = false;
        if (args.contains("--dry")) {
            System.out.println("[CLI] Dry-run mode");
            HelloController.runDryRun();
            handled = true;
        } else if (args.contains("--script1rpa")) {
            System.out.println("[CLI] Script1-RPA mode");
            HelloController.runScript1Rpa();
            handled = true;
        }
        return handled;
    }

    @Override
    public void stop() {
        System.out.println("[STOP] Cleaning up resources");
        // TODO: save state, close threads
    }

    public static void main(String[] args) {
        // JavaFX launch must be last
        launch(args);
    }
}