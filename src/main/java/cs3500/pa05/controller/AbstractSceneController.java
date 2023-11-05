package cs3500.pa05.controller;

import cs3500.pa05.model.WeekData;
import javafx.stage.Stage;

/**
 * Abstracted common components of all scene controllers
 */
public abstract class AbstractSceneController implements SceneController {
  /**
   * The primary stage for all controllers. There should only be one.
   */
  protected Stage mainStage;
  /**
   * The data for a week.
   */
  protected WeekData weekData;

  /**
   * Primary constructor
   *
   * @param mainStage The primary stage for this application (only one)
   * @param weekData  The data for this week
   */
  public AbstractSceneController(Stage mainStage, WeekData weekData) {
    this.weekData = weekData;
    this.mainStage = mainStage;
  }
}
