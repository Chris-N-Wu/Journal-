package cs3500.pa05.view;

import cs3500.pa05.controller.PasswordController;

/**
 * Displays the Password screen
 */
public class PasswordView extends AbstractSceneView {

  /**
   * Default constructor for viewing the password screen. Takes in only a passwordController as
   * this is one of the two basic views (password and splash)
   *
   * @param passwordController The controller for a password screen
   */
  public PasswordView(PasswordController passwordController) {
    super();
    this.loader.setLocation(getClass().getClassLoader().getResource("password.fxml"));
    this.loader.setController(passwordController);
  }
}
