package cs3500.pa05.view;

import cs3500.pa05.controller.SceneController;

/**
 * Viewer for the week display scene
 */
public class WeekDisplayView extends AbstractSceneView {

  /**
   * Default constructor for a week.
   *
   * @param controller The controller to be associated with this scene.
   */
  public WeekDisplayView(SceneController controller) {
    super();
    this.loader.setLocation(getClass().getClassLoader().getResource("weekDisplay2.fxml"));
    this.loader.setController(controller);
  }
}
