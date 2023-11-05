package cs3500.pa05.view;

import cs3500.pa05.controller.SplashController;

/**
 * Loads the splash screen
 */
public class SplashView extends AbstractSceneView {
  /**
   * Default constructor for viewing the splash screen. Takes in only a SplashController as
   * this is one of the two basic views (password and splash)
   *
   * @param splashController The controller for a splash screen
   */
  public SplashView(SplashController splashController) {
    super();
    this.loader.setLocation(getClass().getClassLoader().getResource("splash.fxml"));
    this.loader.setController(splashController);
  }
}
