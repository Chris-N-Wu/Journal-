package cs3500.pa05.controller;

import cs3500.pa05.model.TaskJson;
import cs3500.pa05.model.WeekData;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the creation of a Task. This is its own separate scene
 */
public class TaskCreationController extends SecondarySceneController {
  @FXML
  private TextField taskName;
  @FXML
  private ChoiceBox<String> dayOfWeek;
  @FXML
  private CheckBox taskCompleted;
  @FXML
  private TextField taskDescription;
  @FXML
  private Label errorMessage;
  @FXML
  private Button deleteTaskButton;
  @FXML
  private Button saveTaskButton;
  private TaskJson currTask;

  /**
   * Default constructor for creation of a task
   *
   * @param stage    The primary stage for this application (only one)
   * @param weekData The data for this week
   */
  public TaskCreationController(Stage stage, WeekData weekData) {
    super(stage, weekData);
  }

  /**
   * Gets this application scene going. Starts up basic resources.
   */
  @Override
  public void run() {
    initItems();
  }

  private void initItems() {
    ObservableList<String> daysList = FXCollections.observableList(
        Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    );
    dayOfWeek.setItems(daysList);

    saveTaskButton.setOnAction(event -> saveTaskButtonHandler());
    deleteTaskButton.setOnAction(event -> deleteTaskButtonHandler());
  }

  /**
   * Saves this event
   */
  private void saveTaskButtonHandler() {
    if (this.checkValidInput()) {
      mainStage.setScene(this.weekDisplayView.getScene());
      this.weekData.addTask(
          taskName.getText(),
          dayOfWeek.getSelectionModel().getSelectedIndex(),
          taskDescription.getText(),
          taskCompleted.isSelected()
      );
      weekDisplayController.checkMaxes();
    }
  }

  /**
   * Deletes this task
   */
  private void deleteTaskButtonHandler() {
    weekDisplayController.removeScheduleTask(currTask);
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

    return true;
  }

  /**
   * sets the event dialogue to the specified task's fields
   *
   * @param selectedTask The task that the user selected.
   */
  public void setPrefills(TaskJson selectedTask) {
    currTask = selectedTask;
    taskName.setText(selectedTask.name());
    dayOfWeek.setValue(selectedTask.dow());
    taskCompleted.setSelected(selectedTask.completed());
    taskDescription.setText(selectedTask.description());
  }
}