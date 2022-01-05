module mj.openadventure.openadventuregui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires eu.hansolo.tilesfx;

    opens mj.openadventure.openadventuregui to javafx.fxml;
    exports mj.openadventure.openadventuregui;
}