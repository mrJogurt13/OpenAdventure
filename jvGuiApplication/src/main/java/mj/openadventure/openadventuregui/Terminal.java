package mj.openadventure.openadventuregui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class Terminal {
    @FXML
    private TextArea ta_console;

    @FXML
    private AnchorPane ap_main;

    @FXML
    protected void initialize(){
        ta_console.setText("YHello");
        System.out.println(ta_console.getText());
    }
}