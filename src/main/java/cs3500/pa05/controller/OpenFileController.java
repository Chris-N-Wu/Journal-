package cs3500.pa05.controller;

import cs3500.pa05.model.WeekData;
import cs3500.pa05.view.PasswordView;
import java.nio.file.Path;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for opening a file. This will typically be the first controller to be used.
 */
public class OpenFileController extends SecondarySceneController {
  @FXML
  private Button openFileButton;
  @FXML
  private TextField fileInput;
  @FXML
  private Label pathErrorMessage;

  /**
   * Default controller for opening a file
   *
   * @param mainStage The primary stage for this application (only one)
   * @param weekData  The data for this week
   */
  public OpenFileController(Stage mainStage, WeekData weekData) {
    super(mainStage, weekData);
  }

  /**
   * Initializes all resources necessary for this controller
   */
  @FXML
  public void run() {
    initButtons();
  }

  /**
   * Initializes the open file button
   */
  @FXML
  private void initButtons() {
    openFileButton.setOnAction(event -> submitButtonHandler());
  }

  /**
   * Handles the action when a button is pressed
   */
  private void submitButtonHandler() {
    String userInput = fileInput.getText();
    if (!userInput.endsWith(".bujo")) {
      pathErrorMessage.setText("You must enter a .bujo file");
      return;
    }
    Path weekFile = Path.of(userInput);

    // Validates that the bujo file is correct
    if (WeekData.validBujoFile(weekFile)) {
      // The scene for the Week Display
      Scene weekScene = weekDisplayView.getScene();
      weekData.setCurrentWeekData(weekFile);

      // Checks if this .bujo file is password protected, if it is, prompts the user for a password.
      if (weekData.hasPassword()) {
        // Setting the controller and viewer for password
        PasswordController passwordController =
            new PasswordController(this.mainStage, weekScene, weekDisplayController, this.weekData);
        PasswordView passwordView = new PasswordView(passwordController);

        // Setting the scene
        mainStage.setScene(passwordView.load());
        mainStage.show();

        passwordController.run();
      } else {
        mainStage.setScene(weekDisplayView.getScene());
        this.weekDisplayController.loadStartConditions();
      }


    } else {
      pathErrorMessage.setText(".bujo file is formatted incorrectly or inaccessible");
    }
  }
}
