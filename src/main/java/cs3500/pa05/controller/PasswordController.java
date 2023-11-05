package cs3500.pa05.controller;

import cs3500.pa05.model.WeekData;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for a Password scene
 */
public class PasswordController {
  @FXML
  private Label passwordErrorMessage;
  @FXML
  private TextField passwordField;
  @FXML
  private Button submitPassword;
  private final Stage primaryStage;
  private final Scene weekDisplay;
  private final WeekData weekData;
  private final WeekDisplayController weekDisplayController;

  /**
   * Default constructor for a Password scene. Prompts a user to input the week's password.
   *
   * @param primaryStage          The stage where each scene is displayed on. There should only be
   *                              one.
   * @param weekDisplay           The primary scene for this application. Set to the week.
   * @param weekDisplayController The controller for this week.
   * @param weekData              Data for this week (contains a .bujo)
   */
  public PasswordController(Stage primaryStage, Scene weekDisplay,
                            WeekDisplayController weekDisplayController, WeekData weekData) {
    this.primaryStage = primaryStage;
    this.weekDisplay = weekDisplay;
    this.weekData = weekData;
    this.weekDisplayController = weekDisplayController;
  }

  /**
   * Starts this controller
   */
  @FXML
  public void run() {
    this.initSubmitButton();
  }

  /**
   * Handles a user submitting their password
   */
  @FXML
  private void initSubmitButton() {
    this.submitPassword.setOnAction(event -> {
      // Checks if the inputted password matches the password in the file
      if (this.weekData.validatePassword(this.passwordField.getText())) {
        this.primaryStage.setScene(weekDisplay);
        this.weekDisplayController.loadStartConditions();
        this.primaryStage.show();
      } else {
        this.passwordErrorMessage.setText("Invalid Password");
      }
    });
  }

}
