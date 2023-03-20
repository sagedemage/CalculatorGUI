package com.example.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        TextField text_field = new TextField();
        Button button = new Button("button");
        TilePane tile_pane = new TilePane();

        tile_pane.getChildren().add(text_field);
        tile_pane.getChildren().add(button);

        Scene scene = new Scene(tile_pane, 320, 240);
        stage.setTitle("Java FX GUI App");
        stage.setScene(scene);
        stage.show();

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                String user_input = text_field.getText();

                String calc_friendly_text = add_space_between_operations(user_input);

                List<String> text_list = new ArrayList(Arrays.asList(calc_friendly_text.split(" ")));

                String answer = calculate(text_list);

                text_field.setText("= " + answer);
            }
        };

        button.setOnAction(event);
    }

    public String add_space_between_operations(String user_input) {
        /* Add space between operations of user input */

        String text = "";

        for (int i = 0; i < user_input.length(); ++i) {
            char a = user_input.charAt(i);

            if (i == user_input.length() -1) {
                text += a;
                break;
            }

            char b = user_input.charAt(i+1);

            if (Character.isDigit(a) && b == '+') {
                // 1 +
                text = text + a + " ";
            }

            else if (a == '+' && Character.isDigit(b)) {
                // + 1
                text = text + a + " ";
            }

            else {
                text += a;
            }
        }

        return text;
    }

    public String calculate(List<String> text_list) {

        for (int i = 0; i < text_list.size(); ++i) {
            String a = text_list.get(i);

            if (a.equals("+")) {
                int value1 = Integer.parseInt(text_list.get(i-1));
                int value2 = Integer.parseInt(text_list.get(i+1));
                Integer result = value1 + value2;

                text_list.set(i-1, result.toString());
                text_list.remove(i);
                text_list.remove(i);

                i = 0;
            }
        }

        String answer = text_list.get(0);

        return answer;
    }

    public static void main(String[] args) {
        launch();
    }
}