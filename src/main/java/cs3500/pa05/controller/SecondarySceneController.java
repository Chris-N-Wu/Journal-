package cs3500.pa05.controller;

import cs3500.pa05.model.WeekData;
import cs3500.pa05.view.SceneView;
import javafx.stage.Stage;

/**
 * Abstract class for all controllers that are not the WeekDisplay.
 */
public abstract class SecondarySceneController extends AbstractSceneController {
  /**
   * The viewer associated with a controller
   */
  protected SceneView weekDisplayView;
  /**
   * The controller for a WeekDisplay. Used to switch back to a WeekDisplay.
   */
  protected WeekDisplayController weekDisplayController;

  /**
   * Default constructor
   *
   * @param mainStage The primary stage for this application (only one)
   * @param weekData  The data for the week
   */
  SecondarySceneController(Stage mainStage, WeekData weekData) {
    super(mainStage, weekData);
  }

  /**
   * Sets the displays
   *
   * @param weekDisplayView The Display for the week
   * @param controller      The controller for the week
   */
  public void setWeekDisplay(SceneView weekDisplayView, WeekDisplayController controller) {
    this.weekDisplayView = weekDisplayView;
    this.weekDisplayController = controller;
  }
}
