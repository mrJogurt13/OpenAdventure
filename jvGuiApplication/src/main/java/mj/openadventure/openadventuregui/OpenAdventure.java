package mj.openadventure.openadventuregui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenAdventure extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OpenAdventure.class.getResource("layouts/terminal-view.fxml"));
        stage.setTitle("OpenAdventure"); //TODO: TextAdventure name
        stage.setScene(fxmlLoader.load());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}