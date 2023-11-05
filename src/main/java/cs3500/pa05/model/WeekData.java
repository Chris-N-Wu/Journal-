package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.controller.WeekDisplayController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.collections.ObservableList;

/**
 * To contain the data for the current session of bullet journaling
 */
public class WeekData {
  private BujoJson currentWeekData;
  private Path weekDataPath;

  /**
   * Getter for this week's data
   *
   * @return A BujoJson representing the current week's data
   */
  public BujoJson getCurrentBujoWeekData() {
    return currentWeekData;
  }

  /**
   * Checks if the given path is a validly formatted .bujo file
   *
   * @param path the path to be checked
   * @return true if the given path is a valid bujo file
   */
  public static boolean validBujoFile(Path path) {
    ObjectMapper mapper = new ObjectMapper();
    JsonParser parser;
    try {
      parser = mapper.createParser(path.toFile());
      parser.readValueAs(BujoJson.class);

      parser.close();
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  /**
   * Sets the current week data to the given
   *
   * @param path the path to the .bujo file to be set to
   */
  public void setCurrentWeekData(Path path) {
    ObjectMapper mapper = new ObjectMapper();
    JsonParser parser;
    BujoJson bujoJson;
    try {
      parser = mapper.createParser(path.toFile());
      bujoJson = parser.readValueAs(BujoJson.class);

      parser.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Must be given the path to a valid bujo file");
    }
    currentWeekData = bujoJson;
    weekDataPath = path;
  }

  /**
   * Getter for the tasks for this week
   *
   * @return An ObservableList of TaskJsons of this week
   */
  public ObservableList<TaskJson> getTasks() {
    return currentWeekData.getObservableTasks();
  }

  /**
   * Adds a task to the specified day
   *
   * @param name        The name of the task
   * @param dow         The day of week of the task
   * @param description A description of the task
   * @param completed   Whether the task has been completed
   */
  public void addTask(String name, int dow, String description, boolean completed) {

    this.currentWeekData.scheduleItems().get(dow).getObservableTasks().add(
        new TaskJson(name, intToDow(dow), description, completed));
  }

  /**
   * Adds an event to the specified day
   *
   * @param name        The name of the event
   * @param startTime   The start time of the event
   * @param endTime     The end time of the event
   * @param dow         The day of week of the event
   * @param description A description of the event
   */
  public void addEvent(String name, String startTime, String endTime, int dow,
                       String description) {
    String dowString = intToDow(dow);
    this.currentWeekData.scheduleItems().get(dow).getObservableEvents().add(
        new EventJson(name, dowString, description, startTime, endTime)
    );
  }

  /**
   * Converts an int representing the day of week to a day
   *
   * @param dowNum The day of week number
   * @return A string representing the day of week.
   */
  private static String intToDow(int dowNum) {
    return switch (dowNum) {
      case 0 -> "Sunday";
      case 1 -> "Monday";
      case 2 -> "Tuesday";
      case 3 -> "Wednesday";
      case 4 -> "Thursday";
      case 5 -> "Friday";
      case 6 -> "Saturday";

      default -> throw new IllegalArgumentException("only 7 days in the week");
    };
  }

  /**
   * Verifies that the week contains a password
   *
   * @return True if the .bujo file contains a password
   */
  public boolean hasPassword() {
    return this.currentWeekData.weekData().hasPassword();
  }

  /**
   * Checks that a user inputted password is correct
   *
   * @param password The user inputted password
   * @return True if the passwords match
   */
  public boolean validatePassword(String password) {
    return this.currentWeekData.weekData().validatePassword(password);
  }


  /**
   * to save changes to bujo file
   *
   * @param sundayStart whether the week starts on sunday
   * @param weekName the week name
   * @param theme the week color theme
   * @param maxEvents the week's maximum events
   * @param maxTasks the week's maximum tasks
   */
  public void save(Boolean sundayStart, String weekName, String theme,
                   int maxEvents, int maxTasks) {
    try {
      currentWeekData.weekData().setSundayStart(sundayStart);
      currentWeekData.weekData().setTitle(weekName);
      currentWeekData.weekData().setTheme(theme);
      currentWeekData.weekData().setMaxEvents(maxEvents);
      currentWeekData.weekData().setMaxTasks(maxTasks);
      Files.write(weekDataPath,
          new ObjectMapper().writeValueAsString(this.currentWeekData).getBytes());
    } catch (IOException e) {
      throw new IllegalStateException("WeekData cannot serialize bujo to save");
    }
  }
}
