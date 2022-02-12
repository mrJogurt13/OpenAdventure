package mj.openadventure.openadventuregui;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.css.Style;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Terminal {
    @FXML
    private TextArea ta_console;

    @FXML
    private TextFlow tf_console;

    @FXML
    private AnchorPane ap_main;

    @FXML
    private Scene sc_main;

    @FXML
    protected void initialize(){
        //ta_console.setText("> ");
        //ta_console.positionCaret(2);
        printScreen("welcome", false);

    }

    /**
     * event handler for keystrokes
     * reads command when "enter" is detected
     * determines if the user is allowed to delete text (only the text he has written himself in the last line)
     *
     * @param keyEvent  key stroke
     */
    public void keyStrokes(KeyEvent keyEvent){
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            StringBuilder sb = new StringBuilder();
            for (Node n: tf_console.getChildren()) {
                if(n instanceof Text) {
                    sb.append(((Text) n).getText());
                }
            }
            String[] screenText = sb.toString().split("\n\n> ");
            String usrInput = screenText[screenText.length-1];
            String nextscreen = null;

            //check command (usrInput); get back next screen

            Text t = new Text(keyEvent.getText());
            tf_console.getChildren().add(t);

            if(nextscreen != null) {
                //open next screen
            }
            else{
                Text t1 = new Text("Command ");
                Text t2 = new Text(usrInput);
                t2.setStyle("-fx-font-style: italic");
                Text t3 = new Text(" not found!");
                List<Text> l = new ArrayList<>();
                l.add(t1);
                l.add(t2);
                l.add(t3);
                printScreen(l, false);
            }
        }
        else if(keyEvent.getCode().equals(KeyCode.BACK_SPACE)){
            List<Text> tfChildren = new ArrayList<>();
            for (Node n: tf_console.getChildren()) {
                if(n instanceof Text) {
                    tfChildren.add((Text) n);
                }
            }
            System.out.println(tfChildren.get(tfChildren.size()-1).getText());
            if(!tfChildren.get(tfChildren.size()-1).getText().equals("\n\n> ")) {
                tfChildren.remove(tfChildren.size() - 1);
            }
            tf_console.getChildren().clear();
            tf_console.getChildren().addAll(tfChildren);
        }
        else{
            Text t = new Text(keyEvent.getText());
            //t.setFill(new Color(255, 255, 255, 255));
            t.setStyle("-fx-fill: #dddddd");
            tf_console.getChildren().add(t);
        }
    }

    /**
     * creates a Text object for printing to screen and executes printing methode
     *
     * @param text  text to print to screen
     * @param clear clear the previous story screen
     */
    public void printScreen(String text, boolean clear){
        List<Text> t = new ArrayList<>();
        List<String> styleSheets = sc_main.getStylesheets();
        Text to = new Text(text);
        /*try {
            //styleSheets.get(0)
            BufferedReader br = new BufferedReader(new FileReader("/run/media/corb/2TB_HDD/Bibliothek/projects/OpenAdventure/jvGuiApplication/src/main/resources/mj/openadventure/openadventuregui/styleSheets/default.css"));
            //to.setStyle(br.lines().collect(Collectors.joining()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        to.getStyleClass().clear();
        to.getStyleClass().add(".text-id");

        t.add(to);
        printScreen(t, clear);
    }

    /**
     * prints text to the game screen
     *
     * @param text  text to print to screen
     * @param clear clear the previous story screen
     */
    public void printScreen(List<Text> text, boolean clear){
        if(clear) {
            tf_console.getChildren().clear();
        }
        text.add(new Text("\n\n> "));
        tf_console.getChildren().addAll(text);
    }
}