package lab3_smart_home1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author mdaao
 */
public class Lab3_Smart_Home1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Title of the application
        Text title = new Text("TV Controller");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setFill(Color.GREY);

        BorderPane pane = new BorderPane();

        // Top section
        VBox top = new VBox();
        top.getChildren().add(title);
        top.setPadding(new Insets(20));
        top.setMargin(title,new Insets(15, 15, 15, 300));
        // Right section - Volume control
        VBox right = new VBox(25);
        right.setPadding(new Insets(20));

        Label volumeLabel = new Label("Volume");
        volumeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Slider volumeSlider = new Slider();
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(40);//the current value
        volumeSlider.setShowTickLabels(true);//showing the numbers as labels or not
        volumeSlider.setShowTickMarks(true);//showing the ticks mark or not
        volumeSlider.setMajorTickUnit(50);//where to put major ticks
        volumeSlider.setMinorTickCount(5);//the number of minor ticks in the slider
        volumeSlider.setBlockIncrement(10);
        right.getChildren().addAll(volumeLabel, volumeSlider);

        // Bottom section 
        VBox bottom = new VBox(20);
        bottom.setPadding(new Insets(20));
        bottom.setStyle("-fx-background-color: #EAEAEA;");

        Label TVS = new Label("TV Status");
        TVS.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        ToggleGroup groupTVS = new ToggleGroup();
        RadioButton TvON = new RadioButton("ON");
        TvON.setToggleGroup(groupTVS);
        RadioButton TvOff = new RadioButton("OFF");
        TvOff.setToggleGroup(groupTVS);
        Image homeImage = new Image("https://thumbs.dreamstime.com/b/house-home-vector-icon-white-background-94261130.jpg");
        ImageView homeImageView = new ImageView(homeImage);
        homeImageView.setFitWidth(25);
        homeImageView.setFitHeight(25);
        HBox bottom2 = new HBox(10);
        bottom2.setPadding(new Insets(10));
        bottom2.setStyle("-fx-background-color: #EAEAEA;");
        Button homeButton = new Button("Home Button", homeImageView);
        Image StopB = new Image("https://cdn-icons-png.flaticon.com/512/54/54377.png");
        ImageView StopBImageView = new ImageView(StopB);
        StopBImageView.setFitWidth(25);
        StopBImageView.setFitHeight(25);
        Button StopBout = new Button("Stop Button", StopBImageView);

        bottom.getChildren().addAll(TVS, TvON, TvOff);
        bottom.setAlignment(Pos.BASELINE_LEFT);

        bottom2.getChildren().addAll(bottom, homeButton, StopBout);
        bottom2.setAlignment(Pos.CENTER);

        // Left section - Input source control
        VBox left = new VBox(15);
        left.setPadding(new Insets(20));
        Label inputLabel = new Label("Input Source");
        inputLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        ComboBox<String> inputSource = new ComboBox<>();
        inputSource.getItems().addAll("HDMI 1", "HDMI 2", "USB");
        inputSource.setValue("HDMI 1");
        left.getChildren().addAll(inputLabel, inputSource);

        // Center section - Current status
        BorderPane center = new BorderPane();
        center.setPadding(new Insets(20));
        center.setStyle("-fx-background-color: #FFFFFF;");
        ImageView prevChannel = new ImageView(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaELv0Q5Z38AvmaIurVwQgbzQK4QDvSVEAGxOxXeMU_yfyAACHMUUI1xTfphNJlboM20s&usqp=CAU"));
        prevChannel.setFitWidth(20);
        prevChannel.setFitHeight(20);
        Button prevChannelButton = new Button("Previous Channel", prevChannel);
        center.setAlignment(prevChannelButton, Pos.CENTER);
        center.setLeft(prevChannelButton);

        ImageView nextChannel = new ImageView(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMS3lFyh1r8HqkuyNTZh8_UtKRhq0Ty19ZO9uprMRoa2s4u23hufryA0DUr469IrQdkhk&usqp=CAU"));
        nextChannel.setFitWidth(20);
        nextChannel.setFitHeight(20);
        Button nextChannelButton = new Button("Next Channel", nextChannel);
        center.setAlignment(nextChannelButton, Pos.CENTER);

        center.setRight(nextChannelButton);

        Button OkChannelButton = new Button("Ok");
        center.setCenter(OkChannelButton);
       OkChannelButton.setStyle("-fx-background-color: gray; -fx-textfill: white;");

        ImageView topB = new ImageView(new Image("https://cdn-icons-png.flaticon.com/512/55/55284.png"));
        topB.setFitWidth(20);
        topB.setFitHeight(20);
        Button TOP = new Button("Previous Channel", topB);
        center.setAlignment(TOP, Pos.CENTER);

        center.setTop(TOP);
        ImageView nextimage = new ImageView(new Image("https://cdn-icons-png.freepik.com/512/13737/13737709.png"));
        nextimage.setFitWidth(20);
        nextimage.setFitHeight(20);
        Button nextlButton = new Button("Next Channel", nextimage);
        center.setAlignment(nextlButton, Pos.CENTER);

        center.setBottom(nextlButton);

        // Adding sections to the BorderPane
        pane.setTop(top);
        pane.setRight(right);
        pane.setBottom(bottom2);
        pane.setLeft(left);
        pane.setCenter(center);

        Scene scene = new Scene(pane, 800, 500);

        primaryStage.setTitle("Lab3_Smart_Home1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
