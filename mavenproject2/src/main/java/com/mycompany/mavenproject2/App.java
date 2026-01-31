package com.mycompany.mavenproject2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;
import javafx.scene.text.Font;
import org.hibernate.Session;

public class App extends Application {

    private Scene loginScene, homeScene, addEventScene, viewEventsScene;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupLoginScene();
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("User  Authentication");
        primaryStage.show();
    }

    // Setup the login scene
    private void setupLoginScene() {
        BorderPane loginBorderPane = new BorderPane();
        setBackground(loginBorderPane);

        Label loginTitle = createLabel("Login", 40, "#8B4513");
        loginBorderPane.setTop(loginTitle);
        BorderPane.setAlignment(loginTitle, Pos.CENTER);

        VBox loginLayout = createVBox(20, Pos.CENTER);
        Label emailLabel = createLabel("Email:", 20, "#4E342E");
        TextField emailField = new TextField();
        Label passwordLabel = createLabel("Password:", 20, "#4E342E");
        PasswordField passwordField = new PasswordField();
        Label errorLabel = createErrorLabel();
        Button loginButton = createStyledButton("Login");
        loginButton.setOnAction(e -> handleLogin(emailField, passwordField, errorLabel));

        loginLayout.getChildren().addAll(emailLabel, emailField, passwordLabel, passwordField, errorLabel, loginButton);
        loginBorderPane.setCenter(loginLayout);
        loginScene = new Scene(loginBorderPane, 1100, 600);
    }

    private void handleLogin(TextField emailField, PasswordField passwordField, Label errorLabel) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (!isValidEmail(email)) {
            errorLabel.setText("Invalid email format");
        } else if (password.isEmpty()) {
            errorLabel.setText("Password cannot be empty");
        } else {
            if (validateUser (email, password)) {
                errorLabel.setText("Login successful!");
                setupHomePage();
            } else {
                errorLabel.setText("Invalid credentials");
            }
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean validateUser (String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Account> users = session.createQuery("FROM Account WHERE email = :email AND password = :password", Account.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .list();
            return !users.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Setup the home page
    private void setupHomePage() {
        BorderPane homeBorderPane = new BorderPane();
        setBackground(homeBorderPane);

        Label homeTitle = createLabel("Home Page", 40, "#4E342E");
        homeBorderPane.setTop(homeTitle);
        BorderPane.setAlignment(homeTitle, Pos.CENTER);

        HBox buttonBox = createHomeButtons();
        homeBorderPane.setCenter(buttonBox);

        homeScene = new Scene(homeBorderPane, 1100, 600);
        primaryStage.setScene(homeScene);
    }

    // Create buttons for the home page
    private HBox createHomeButtons() {
        Button addEventButton = createStyledButton("Add Event");
        Button viewEventsButton = createStyledButton("View Events");
        Button backButton = createStyledButton("Back to Login");

        HBox buttonBox = new HBox(10, addEventButton, viewEventsButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20));

        addEventButton.setOnAction(e -> openAddEventScene());
        viewEventsButton.setOnAction(e -> openViewEventsScene());
        backButton.setOnAction(e -> primaryStage.setScene(loginScene));

        return buttonBox;
    }

    // Add Event Scene
    private void openAddEventScene() {
        BorderPane addEventBorderPane = new BorderPane();
        setBackground(addEventBorderPane);

        Label addEventTitle = createLabel("Add Event", 40, "#4E342E");
        addEventBorderPane.setTop(addEventTitle);
        BorderPane.setAlignment(addEventTitle, Pos.CENTER);

        VBox addEventLayout = createVBox(20, Pos.CENTER);
        Label eventNameLabel = new Label("Event Name:");
        TextField eventNameField = new TextField();
        Label eventDetailsLabel = new Label("Event Details:");
        TextArea eventDetailsField = new TextArea();

        Label eventDateLabel = new Label("Event Date:");
        DatePicker eventDatePicker = new DatePicker();

        Label eventTimeLabel = new Label("Event Time (HH:MM:SS):");
        TextField eventTimeField = new TextField();

        Label eventLocationLabel = new Label("Event Location:");
        TextField eventLocationField = new TextField();
        Button addEventButton = createStyledButton("Add Event");
        Button backButton = createStyledButton("Back to Home");

        Label eventMessageLabel = new Label();

        addEventButton.setOnAction(e -> {
            String eventName = eventNameField.getText().trim();
            String eventDetails = eventDetailsField.getText().trim();
            LocalDate eventDate = eventDatePicker.getValue();
            String eventTime = eventTimeField.getText().trim();
            String eventLocation = eventLocationField.getText().trim();

            if (eventName.isEmpty() || eventDetails.isEmpty() || eventDate == null || eventTime.isEmpty() || eventLocation.isEmpty()) {
                eventMessageLabel.setText("All fields must be filled out.");
            } else {
                if (addEvent(eventName, eventDetails, eventDate.toString(), eventTime, eventLocation)) {
                    eventMessageLabel.setText("Event added successfully!");
                } else {
                    eventMessageLabel.setText("Failed to add event.");
                }
            }
        });

        backButton.setOnAction(e -> primaryStage.setScene(homeScene));

        addEventLayout.getChildren().addAll(eventNameLabel, eventNameField, eventDetailsLabel, eventDetailsField,
                eventDateLabel, eventDatePicker, eventTimeLabel, eventTimeField, eventLocationLabel, eventLocationField,
                addEventButton, backButton, eventMessageLabel);

        addEventBorderPane.setCenter(addEventLayout);
        Scene addEventScene = new Scene(addEventBorderPane, 1100, 600);
        primaryStage.setScene(addEventScene);
    }

    // Add Event to database
    private boolean addEvent(String eventName, String eventDetails, String eventDate, String eventTime, String eventLocation) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Event newEvent = new Event();
            newEvent.setEventName(eventName);
            newEvent.setEventDetails(eventDetails);
            newEvent.setStatus("Scheduled");
            newEvent.setEventDate(java.sql.Date.valueOf(eventDate));
            newEvent.setEventTime(eventTime);
            newEvent.setEventLocation(eventLocation);

            session.save(newEvent);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Load Events into ListView
    private void loadEvents(ListView<EventItem> eventsListView) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Event> events = session.createQuery("FROM Event ORDER BY eventDate ASC, eventTime ASC", Event.class).list();
            eventsListView.getItems().clear();
            for (Event event : events) {
                String eventDetails = event.getEventName() + " - " + event.getEventDate() + " " + event.getEventTime() + " at " + event.getEventLocation() + " [" + event.getStatus() + "]";
                eventsListView.getItems().add(new EventItem(event.getEventId(), eventDetails)); // Use getEventId()
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Event by ID
    private boolean deleteEvent(int eventId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Event eventToDelete = session.get(Event.class, eventId);
            if (eventToDelete != null) {
                session.delete(eventToDelete);
                session.getTransaction().commit();
                showAlert("Event deleted successfully.");
                return true; // Event deleted successfully
            } else {
                showAlert("Event not found.");
                return false; // Event not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Failed to delete event.");
            return false; // Deletion failed
        }
    }

    // Open View Events Scene
    private void openViewEventsScene() {
        BorderPane viewEventsBorderPane = new BorderPane();
        setBackground(viewEventsBorderPane);

        Label viewEventsTitle = createLabel("View Events", 40, "#4E342E");
        viewEventsBorderPane.setTop(viewEventsTitle);
        BorderPane.setAlignment(viewEventsTitle, Pos.CENTER);

        VBox viewEventsLayout = createVBox(20, Pos.CENTER);
        ListView<EventItem> eventsListView = new ListView<>(); // Use EventItem for ID and details

        loadEvents(eventsListView);
        Button deleteButton = new Button("Delete Event");
        Button backButton = createStyledButton("Back to Home");

        deleteButton.setOnAction(e -> {
            EventItem selectedEvent = eventsListView.getSelectionModel().getSelectedItem();

            if (selectedEvent != null) {
                int eventId = selectedEvent.getId(); // Get the event ID
                boolean deleted = deleteEvent(eventId);

                if (deleted) {
                    eventsListView.getItems().remove(selectedEvent);
                }
            } else {
                showAlert("Please select an event to delete.");
            }
        });

        backButton.setOnAction(e -> primaryStage.setScene(homeScene));

        HBox buttonBox = new HBox(10, deleteButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20));

        viewEventsLayout.getChildren().addAll(eventsListView, buttonBox);
        viewEventsBorderPane.setCenter(viewEventsLayout);
        Scene viewEventsScene = new Scene(viewEventsBorderPane, 1100, 600);
        primaryStage.setScene(viewEventsScene);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setBackground(BorderPane borderPane) {
        Image backgroundImage = new Image("https://img.freepik.com/premium-vector/cute-brown-aesthetic-abstract-minimal-background-perfect-wallpaper-backdrop-postcard-background_565280-444.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(500, 400, false, false, true, true));
        borderPane.setBackground(new Background(background));
    }

    private Label createLabel(String text, int fontSize, String textColor) {
        Label label = new Label(text);
        label.setFont(Font.font("Lucida Console", fontSize));
        label.setStyle("-fx-text-fill: " + textColor + ";");
        return label;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font(18));
        button.setStyle("-fx-background-color: #8D6E63; -fx-text-fill: white; -fx-padding: 12px 30px; -fx-background-radius: 10px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #6D4C41; -fx-text-fill: white; -fx-padding: 12px 30px; -fx-background-radius: 10px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #8D6E63; -fx-text-fill: white; -fx-padding: 12px 30px; -fx-background-radius: 10px;"));
        return button;
    }

    private Label createErrorLabel() {
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        return errorLabel;
    }

    private VBox createVBox(int spacing, Pos alignment) {
        VBox vbox = new VBox(spacing);
        vbox.setAlignment(alignment);
        vbox.setPadding(new Insets(20));
        return vbox;
    }
}

// EventItem class to hold event details
class EventItem {
    private int id;
    private String details;

    public EventItem(int id, String details) {
        this.id = id;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return details; // This will be displayed in the ListView
    }
}