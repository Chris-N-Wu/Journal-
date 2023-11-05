package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Represents a scene viewer
 */
public interface SceneView {
  /**
   * Retrieves the Scene for the current viewer
   *
   * @return A Scene representing whatever the current viewer is
   */
  Scene load();

  /**
   * //TODO: See if can be deleted
   *
   * @return A scene
   */
  Scene getScene();
}
