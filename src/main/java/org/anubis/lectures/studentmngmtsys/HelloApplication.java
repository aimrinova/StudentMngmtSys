package org.anubis.lectures.studentmngmtsys;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class HelloApplication extends Application {

    // Flags for CLI modes
    private static boolean dryrun = false;
    private static boolean launchOnly = false;

    /**
     * Entry point: parses CLI args before deciding to open GUI or run headless tasks.
     */
    public static void main(String[] args) {
        // Parse arguments
        for (String arg : args) {
            if ("--dryrun".equalsIgnoreCase(arg)) {
                dryrun = true;
            } else if ("--launch".equalsIgnoreCase(arg)) {
                launchOnly = true;
            }
        }

        // If --dryrun is specified, run and exit without GUI
        if (dryrun) {
            System.out.println("[DRY RUN] Starting dry-run mode...");
            runDryRun();
            return;
        }

        // If --launch is specified, run and exit without GUI
        if (launchOnly) {
            System.out.println("[LAUNCH] Starting launch-only mode...");
            runLaunchOnly();
            return;
        }

        // No flags: launch JavaFX UI
        launch(args);
    }

    @Override
    public void init() {
        // Called before start(); can perform pre-initialization here
        System.out.println("[INIT] Application is initializing");
    }

    @Override
    public void start(Stage primaryStage) {
        // Build a GridPane container in code (no FXML)
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.TOP_LEFT);

        // Create six buttons and place them in a 2x3 grid
        for (int i = 0; i < 6; i++) {
            Button btn = new Button();
            int row = i / 3;
            int col = i % 3;

            // Label and wire actions for first two buttons
            switch (i) {
                case 0:
                    btn.setText("Dry Run");
                    btn.setOnAction(e -> runDryRun());  // calls same logic as CLI
                    break;
                case 1:
                    btn.setText("Launch Only");
                    btn.setOnAction(e -> runLaunchOnly());
                    break;
                default:
                    btn.setText("Button " + (i + 1));
            }

            gridPane.add(btn, col, row);
        }

        // Wrap grid in a scene and show stage
        Scene scene = new Scene(gridPane, 360, 180);
        primaryStage.setTitle("Script Launcher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        // Called when the application is closing
        System.out.println("[STOP] Application is shutting down");
    }

    /**
     * Example headless dry-run logic (shared between CLI and GUI button)
     */
    private static void runDryRun() {
        // Replace with ProcessBuilder code to perform a dry-run
        System.out.println("[DRY RUN] Would execute process here");
    }

    /**
     * Example headless launch-only logic (shared between CLI and GUI button)
     */
    private static void runLaunchOnly() {
        // Replace with ProcessBuilder code to launch script here
        System.out.println("[LAUNCH] Would launch process here");
    }
}
