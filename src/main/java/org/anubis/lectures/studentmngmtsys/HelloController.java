// --- src/main/java/org/anubis/lectures/studentmngmtsys/HelloController.java ---
package org.anubis.lectures.studentmngmtsys;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

public class HelloController {
    public GridPane buttonGrid;
    public Button btnFeature3;
    @FXML private ProgressIndicator spinner;
    @FXML private ProgressBar progressBar;
    @FXML private Label statusLabel;

    @FXML private Button btnDryRun;
    @FXML private Button btnScript1;

    @FXML
    private void onHome() {
        // Unbind before setting text manually
        statusLabel.textProperty().unbind();
        statusLabel.setText("Home clicked");
    }

    @FXML
    private void onSettings() {
        statusLabel.textProperty().unbind();
        statusLabel.setText("Settings clicked");
    }


    @FXML private void onDryRun() {
        executeAsync(List.of("python", "scripts/my_script.py", "--dryrun"));
    }

    @FXML private void onScript1Rpa() {
        executeAsync(List.of("cmd", "/c", "scripts/rpa_launcher.bat"));
    }

    // Public static for CLI use
    public static void runDryRun() {
        executeSync(List.of("python", "scripts/my_script.py", "--dryrun"));
    }
    public static void runScript1Rpa() {
        executeSync(List.of("cmd", "/c", "scripts/rpa_launcher.bat"));
    }

    private static void executeSync(List<String> cmd) {
        try {
            ProcessBuilder pb = prepareBuilder(cmd);
            var proc = pb.start();
            try (var reader = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) System.out.println(line);
            }
            System.out.println("Exit code: " + proc.waitFor());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void executeAsync(List<String> cmd) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("Running...");
                ProcessBuilder pb = prepareBuilder(cmd);
                Process proc = pb.start();
                try (var reader = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                    String line;
                    int count = 0;
                    while ((line = reader.readLine()) != null) {
                        count++;
                        updateMessage(line);
                    }
                }
                int code = proc.waitFor();
                updateMessage("Finished with code " + code);
                return null;
            }
        };

        // Bindings control the UI—no manual setVisible
        spinner.visibleProperty().bind(task.runningProperty());
        progressBar.visibleProperty().bind(task.runningProperty());
        statusLabel.textProperty().bind(task.messageProperty());

        task.setOnFailed(evt -> {
            Throwable error = task.getException();
            Platform.runLater(() -> {
                statusLabel.textProperty().unbind();
                statusLabel.setText("Error: " + error.getMessage());
                new Alert(Alert.AlertType.ERROR, error.getMessage(), ButtonType.OK)
                        .showAndWait();
            });
        });

        new Thread(task).start();
    }


    private static ProcessBuilder prepareBuilder(List<String> cmd) throws Exception {
        File base;
        try {
            // Works in JAR
            File jar = new File(HelloController.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI());
            base = jar.getParentFile();
        } catch (Exception ex) {
            // Fallback for IDE execution
            base = new File(System.getProperty("user.dir"));
        }

        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.directory(base);
        pb.redirectErrorStream(true);
        return pb;
    }

    public Button btnDryRun() {
        return btnDryRun;
    }

    public void setBtnDryRun(Button btnDryRun) {
        this.btnDryRun = btnDryRun;
    }

    public Button btnScript1() {
        return btnScript1;
    }

    public void setBtnScript1(Button btnScript1) {
        this.btnScript1 = btnScript1;
    }
}
