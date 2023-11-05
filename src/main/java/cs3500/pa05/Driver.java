package cs3500.pa05;

import cs3500.pa05.controller.SplashController;
import cs3500.pa05.view.SplashView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Starts up the Bullet Journaling Application
 */
public class Driver extends Application {

  /**
   * Launches the program
   *
   * @param args No arguments required
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * @param primaryStage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   */
  @Override
  public void start(Stage primaryStage) {
    SplashController splashController = new SplashController(primaryStage);
    SplashView splashView = new SplashView(splashController);
    Scene splashScene = splashView.load();
    primaryStage.setScene(splashScene);
    primaryStage.show();
    splashController.run();
  }
}
