package cs3500.pa05.view;

import cs3500.pa05.controller.SceneController;

/**
 * Viewer for the file opener scene. This is typically the first scene to be shown to the user
 */
public class OpenFileView extends AbstractSceneView {

  /**
   * Default constructor for loading up the scene.
   *
   * @param controller The controller associated with this scene.
   */
  public OpenFileView(SceneController controller) {
    super();
    this.loader.setLocation(getClass().getClassLoader().getResource("openFile.fxml"));
    this.loader.setController(controller);
  }
}
