package cs3500.pa05.view;

import cs3500.pa05.controller.SceneController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * An Abstract class that contains methods that all Viewers use
 */
public abstract class AbstractSceneView implements SceneView {
  /**
   * The FXML loader for loading a scene
   */
  protected FXMLLoader loader;
  /**
   * The scene being used
   */
  protected Scene scene;
  /**
   * The controller for the scene
   */
  protected SceneController controller;

  /**
   * Default constructor. Creates a new FXML loader to be used by viewers.
   */
  public AbstractSceneView() {
    this.loader = new FXMLLoader();
  }

  /**
   * Retrieves the Scene for the current viewer
   *
   * @return A Scene representing whatever the current viewer is
   */
  public Scene load() {
    try {
      this.scene = this.loader.load();
      return this.scene;
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load layout");
    }
  }

  // TODO: Is this necessary?
  /**
   * Getter for a Scene
   *
   * @return Get Scene
   */
  public Scene getScene() {
    return this.scene;
  }
}
