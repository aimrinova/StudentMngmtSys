module org.anubis.lectures.studentmngmtsys {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.prefs;
    requires jakarta.xml.bind;
    opens org.anubis.lectures.studentmngmtsys.util to jakarta.xml.bind;
    opens org.anubis.lectures.studentmngmtsys to javafx.fxml;
    exports org.anubis.lectures.studentmngmtsys;
}