package org.anubis.lectures.studentmngmtsys.controller;

import org.anubis.lectures.studentmngmtsys.MainApp;
import org.anubis.lectures.studentmngmtsys.model.StudentStorage;

public class HomescreenInfo {


    // Reference to the main application.
    private MainApp mainApp;
    private StudentStorage storage;

    public HomescreenInfo() {

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
