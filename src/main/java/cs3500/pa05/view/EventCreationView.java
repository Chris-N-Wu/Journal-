package cs3500.pa05.view;

import cs3500.pa05.controller.SceneController;

/**
 * Viewer for the event creation scene
 */
public class EventCreationView extends AbstractSceneView {

  /**
   * Default constructor for creating an event
   *
   * @param controller The controller that is associated with this viewer
   */
  public EventCreationView(SceneController controller) {
    super();
    this.loader.setLocation(getClass().getClassLoader().getResource("eventCreation.fxml"));
    this.loader.setController(controller);
  }
}
