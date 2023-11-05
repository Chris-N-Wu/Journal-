package cs3500.pa05.view;

import cs3500.pa05.controller.SceneController;

/**
 * Viewer for the task creation scene
 */
public class TaskCreationView extends AbstractSceneView {

  /**
   * Default constructor for viewing the task creation pane
   *
   * @param controller The controller associated with creating a task
   */
  public TaskCreationView(SceneController controller) {
    super();
    this.loader.setLocation(getClass().getClassLoader().getResource("taskCreation.fxml"));
    this.loader.setController(controller);
  }
}
