import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChannelNavigator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        

        Rectangle largeRectangle = new Rectangle(400, 400, Color.WHITE);
        largeRectangle.setX(250);
        largeRectangle.setY(100);
        largeRectangle.setStroke(Color.RED);

        Rectangle smallRectangle = new Rectangle(100, 100, Color.BLUE);
        smallRectangle.setX(100);
        smallRectangle.setY(150);
        smallRectangle.setFocusTraversable(true); // Make the small rectangle focusable

        ComboBox<String> colorComboBox = new ComboBox<>();
        colorComboBox.getItems().addAll("Green", "Red", "Blue");
        colorComboBox.setOnAction(e -> {
            String color = colorComboBox.getValue();
            if (color.equals("Green")) {
                smallRectangle.setFill(Color.GREEN);
            } else if (color.equals("Red")) {
                smallRectangle.setFill(Color.RED);
            } else if (color.equals("Blue")) {
                smallRectangle.setFill(Color.BLUE);
            }
        });

        Button rotateLeftButton = new Button("<<");
        rotateLeftButton.setOnAction(e -> smallRectangle.setRotate(smallRectangle.getRotate() - 90));

        Button rotateRightButton = new Button(">>");
        rotateRightButton.setOnAction(e -> smallRectangle.setRotate(smallRectangle.getRotate() + 90));

        CheckBox strokeCheckBox = new CheckBox("Add Stroke?");
        strokeCheckBox.setOnAction(e -> {
            if (strokeCheckBox.isSelected()) {
                smallRectangle.setStroke(Color.RED);
                smallRectangle.setStrokeWidth(3);
            } else {
                smallRectangle.setStroke(null);
            }
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(largeRectangle, smallRectangle);
        pane.getChildren().addAll(stackPane, colorComboBox, rotateLeftButton, rotateRightButton, strokeCheckBox);

        Scene scene = new Scene(pane, 600, 600);

        // Key event for moving the small rectangle
        scene.setOnKeyPressed(event -> {
            double x = smallRectangle.getX();
            double y = smallRectangle.getY();
            switch (event.getCode()) {
                case UP:
                    if (y > 0) smallRectangle.setY(y - 10); // Move up
                    break;
                case DOWN:
                    if (y < largeRectangle.getHeight() - smallRectangle.getHeight()) smallRectangle.setY(y + 10); // Move down
                    break;
                case LEFT:
                    if (x > 0) smallRectangle.setX(x - 10); // Move left
                    break;
                case RIGHT:
                    if (x < largeRectangle.getWidth() - smallRectangle.getWidth()) smallRectangle.setX(x + 10); // Move right
                    break;
                default:
                    break;
            }
            event.consume(); // Prevent default KeyEvent processing
        });

        // Mouse event for changing the pane background color
        pane.setOnMouseClicked(event -> {
            if (event.getTarget() == pane) {
                pane.setStyle("-fx-background-color: lightyellow;");
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Event-Driven Programming Example with Nested Rectangles");
        primaryStage.show();

        // Request focus on the small rectangle to capture key events
        smallRectangle.requestFocus();
    }
}