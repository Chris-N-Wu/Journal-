package cs3500.pa05.controller;

import cs3500.pa05.model.EventJson;
import cs3500.pa05.model.WeekData;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for creating new events
 */
public class EventCreationController extends SecondarySceneController {
  @FXML
  private TextField eventName;
  @FXML
  private ChoiceBox<String> dayOfWeek;
  @FXML
  private TextField startTime;
  @FXML
  private TextField endTime;
  @FXML
  private TextField eventDescription;
  @FXML
  private Button deleteEventButton;
  @FXML
  private Button saveEventButton;
  @FXML
  private Label errorMessage;
  private EventJson currEvent;

  /**
   * Constructor for event controller
   *
   * @param stage    The primary stage that this program uses (only one)
   * @param weekData The current data for the week
   */
  public EventCreationController(Stage stage, WeekData weekData) {
    super(stage, weekData);
  }

  /**
   * Method to be called to start event creation
   */
  @Override
  public void run() {
    initItems();
  }

  /**
   * Initializes the items that can be used as days of week
   */
  private void initItems() {
    ObservableList<String> daysList = FXCollections.observableList(Arrays.asList(
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday")
    );
    dayOfWeek.setItems(daysList);

    saveEventButton.setOnAction(event -> saveEventButtonHandler());
    deleteEventButton.setOnAction(event -> deleteEventButtonHandler());
  }

  /**
   * Handles saving an event
   */
  private void saveEventButtonHandler() {
    if (this.checkValidInput()) {
      mainStage.setScene(this.weekDisplayView.getScene());
      this.weekData.addEvent(
          eventName.getText(),
          startTime.getText(),
          endTime.getText(),
          dayOfWeek.getSelectionModel().getSelectedIndex(),
          eventDescription.getText()
      );
      weekDisplayController.checkMaxes();
    }
  }

  /**
   * handles deleting an event
   */
  private void deleteEventButtonHandler() {
    weekDisplayController.removeScheduleEvent(currEvent);
    mainStage.setScene(this.weekDisplayView.getScene());
  }


  /**
   * Checker for if a user's input is valid
   *
   * @return True if valid
   */
  private boolean checkValidInput() {
    // If the user does not select a Day-Of-Week, they cannot proceed
    if (this.dayOfWeek.getSelectionModel().getSelectedIndex() == -1) {
      this.errorMessage.setText("You Must Select A Day");
      return false;
    }
    String start = this.startTime.getText();
    String end = this.endTime.getText();
    try {
      LocalTime.parse(start);
      LocalTime.parse(end);
    } catch (DateTimeParseException | NullPointerException e) {
      this.errorMessage.setText("Time must be in HH:MM format");
      return false;

    }

    return true;
  }

  /**
   * Used to set the fields of a task when editing a task.
   *
   * @param selectedEvent The event information
   */
  public void setPrefills(EventJson selectedEvent) {
    currEvent = selectedEvent;
    eventName.setText(selectedEvent.name());
    dayOfWeek.setValue(selectedEvent.dow());
    startTime.setText(selectedEvent.startTime());
    endTime.setText(selectedEvent.endTime());
    eventDescription.setText(selectedEvent.description());

  }
}
