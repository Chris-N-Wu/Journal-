package cs3500.pa05.controller;

import cs3500.pa05.model.WeekData;
import cs3500.pa05.view.EventCreationView;
import cs3500.pa05.view.OpenFileView;
import cs3500.pa05.view.TaskCreationView;
import cs3500.pa05.view.WeekDisplayView;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the splash scene. This scene should be the first scene displayed.
 */
public class SplashController {
  @FXML
  private Button continueToController;
  private final Stage primaryStage;

  /**
   * Default constructor for a Splash scene.
   *
   * @param primaryStage The stage where each scene is displayed on. There should only be one.
   */
  public SplashController(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  /**
   * Initializes the button to its events and starts the controller.
   */
  @FXML
  public void run() {
    initContinueButton();
  }

  /**
   * Initializes the button and actions.
   */
  @FXML
  private void initContinueButton() {
    this.continueToController.setOnAction(event -> proceedToOpenFile());
  }

  /**
   * Once the button has been pressed, continue to main program.
   */
  private void proceedToOpenFile() {
    WeekData weekData = new WeekData();
    OpenFileController openFileController =
        new OpenFileController(primaryStage, weekData);
    OpenFileView openFileView = new OpenFileView(openFileController);
    Scene openFileScene = openFileView.load();

    EventCreationController
        createEventController = new EventCreationController(primaryStage, weekData);
    EventCreationView createEvent = new EventCreationView(createEventController);
    Scene createEventScene = createEvent.load();

    TaskCreationController createTaskController =
        new TaskCreationController(primaryStage, weekData);
    TaskCreationView createTask = new TaskCreationView(createTaskController);
    Scene createTaskScene = createTask.load();

    WeekDisplayController weekDisplayController = new WeekDisplayController(
        primaryStage,
        weekData,
        openFileScene, createEventScene, createTaskScene, createTaskController,
        createEventController);
    WeekDisplayView weekDisplayView = new WeekDisplayView(weekDisplayController);
    weekDisplayView.load();

    openFileController.setWeekDisplay(weekDisplayView, weekDisplayController);
    createEventController.setWeekDisplay(weekDisplayView, weekDisplayController);
    createTaskController.setWeekDisplay(weekDisplayView, weekDisplayController);

    primaryStage.setScene(openFileScene);
    primaryStage.show();
    openFileController.run();
    createEventController.run();
    createTaskController.run();
    weekDisplayController.run();
  }
}
