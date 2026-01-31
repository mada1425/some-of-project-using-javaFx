package lab4event;

import javafx.application.Application;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.*;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LAB4Event extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane paneA = new BorderPane();
        paneA.setPadding(new Insets(20, 20, 20, 20));

        Pane pane = new Pane();
        Rectangle rectangle = new Rectangle(90, 150, Color.GREEN);
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setStroke(Color.RED);
        rectangle.setStrokeWidth(3);
        rectangle.setFocusTraversable(true);
        pane.getChildren().add(rectangle);
        pane.setStyle("-fx-border-color: BLUE; -fx-background-color:white; -fx-border-width: 4;");
        paneA.setCenter(pane);
        pane.setFocusTraversable(true);

        HBox left1C = new HBox(10);
        left1C.getChildren().add(new Label("Fill Color:"));
        ComboBox<String> colorComboBox = new ComboBox<>();
        colorComboBox.getItems().addAll("Green", "Red", "Blue");
        colorComboBox.setOnAction(e -> {
            String color = colorComboBox.getValue();
            if (color.equals("Green")) {
                rectangle.setFill(Color.GREEN);
            } else if (color.equals("Red")) {
                rectangle.setFill(Color.RED);
            } else if (color.equals("Blue")) {
                rectangle.setFill(Color.BLUE);
            }
        });
        colorComboBox.setFocusTraversable(false);
        left1C.getChildren().add(colorComboBox);

        HBox left2B = new HBox(10);
        left2B.getChildren().add(new Label("Rotate:"));
        Button rotateLeftButton = new Button("<<");
        Button rotateRightButton = new Button(">>");
        left2B.getChildren().addAll(rotateLeftButton, rotateRightButton);

        HBox left3CB = new HBox(40);
        CheckBox strokeCheckBox = new CheckBox("Add Stroke?");
        left3CB.getChildren().addAll(strokeCheckBox);

        VBox leftall = new VBox(40);
        leftall.setPadding(new Insets(60, 15, 15, 15));
        leftall.getChildren().addAll(left1C, left2B, left3CB);
        paneA.setLeft(leftall);
        BorderPane.setMargin(pane, new Insets(20, 40, 20, 20));

        Scene scene = new Scene(paneA, 600, 600);

        // Add key event handler
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (rectangle.getY() - 10 >= 0) {
                        rectangle.setY(rectangle.getY() - 10);
                    }
                    event.consume();
                    break;
                case DOWN:
                    if (rectangle.getY() + rectangle.getHeight() + 10 <= pane.getHeight()) {
                        rectangle.setY(rectangle.getY() + 10);
                    }
                    event.consume();
                    break;
                case LEFT:
                    if (rectangle.getX() - 10 >= 0) {
                        rectangle.setX(rectangle.getX() - 10);
                    }
                    event.consume();
                    break;
                case RIGHT:
                    if (rectangle.getX() + rectangle.getWidth() + 10 <= pane.getWidth()) {
                        rectangle.setX(rectangle.getX() + 10);
                    }
                    event.consume(); // Prevent default processing
                    break;
                default:
                    break;
            }
        });
        rectangle.requestFocus();

        // Add mouse event handler
        pane.setOnMouseClicked(event -> {
            if (event.getPickResult().getIntersectedNode() == pane) { // Check if the click is on the pane itself
                pane.setStyle("-fx-background-color: lightyellow;");
            }
            event.consume(); // Prevent default processing 
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Lab 4 Event-Driven ");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
