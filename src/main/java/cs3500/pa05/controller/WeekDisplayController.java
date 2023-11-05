package cs3500.pa05.controller;

import cs3500.pa05.model.EventJson;
import cs3500.pa05.model.TaskJson;
import cs3500.pa05.model.Theme;
import cs3500.pa05.model.WeekData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controls the user experience when displaying a week
 */
public class WeekDisplayController extends AbstractSceneController {
  @FXML // String is placeholder
  private ListView<TaskJson> weekTaskList;
  @FXML
  private ListView<EventJson> sundayEventList;
  @FXML
  private ListView<TaskJson> sundayTaskList;
  @FXML
  private ListView<EventJson> mondayEventList;
  @FXML
  private ListView<TaskJson> mondayTaskList;
  @FXML
  private ListView<EventJson> tuesdayEventList;
  @FXML
  private ListView<TaskJson> tuesdayTaskList;
  @FXML
  private ListView<EventJson> wednesdayEventList;
  @FXML
  private ListView<TaskJson> wednesdayTaskList;
  @FXML
  private ListView<EventJson> thursdayEventList;
  @FXML
  private ListView<TaskJson> thursdayTaskList;
  @FXML
  private ListView<EventJson> fridayEventList;
  @FXML
  private ListView<TaskJson> fridayTaskList;
  @FXML
  private ListView<EventJson> saturdayEventList;
  @FXML
  private ListView<TaskJson> saturdayTaskList;
  @FXML
  private Button openFileButton;
  @FXML
  private Button saveToFileButton;
  @FXML
  private Label taskMaxError;
  @FXML
  private Label eventMaxError;
  @FXML
  private TextField weekName;
  @FXML
  private TextField maxEvents;
  @FXML
  private TextField maxTasks;
  @FXML
  private MenuButton create;
  @FXML
  private MenuButton setTheme;
  @FXML
  private Label eventStats;
  @FXML
  private Label taskStats;
  @FXML
  private ProgressBar taskProgressBar;
  @FXML
  private VBox sunday;
  @FXML
  private VBox monday;
  @FXML
  private VBox tuesday;
  @FXML
  private VBox wednesday;
  @FXML
  private VBox thursday;
  @FXML
  private VBox friday;
  @FXML
  private VBox saturday;
  @FXML
  private Button shift;
  @FXML
  private GridPane weekGrid;


  private final Scene openFileScene;
  private final Scene createEventScene;
  private final Scene createTaskScene;
  private final TaskCreationController taskController;
  private final EventCreationController eventController;
  private String currentTheme;
  private boolean sundayStart;

  /**
   * @param mainStage        The primary stage (only one)
   * @param weekData         The data for this week
   * @param openFileScene    Navigation to the open file scene
   * @param createEventScene Navigation to the creation event scene
   * @param createTaskScene  Navigation to the creation task scene
   * @param taskController   Controller for creating tasks
   * @param eventController  Controller for creating events
   */
  public WeekDisplayController(Stage mainStage, WeekData weekData, Scene openFileScene,
                               Scene createEventScene, Scene createTaskScene, TaskCreationController
                                   taskController, EventCreationController eventController) {
    super(mainStage, weekData);
    this.openFileScene = openFileScene;
    this.createEventScene = createEventScene;
    this.createTaskScene = createTaskScene;
    this.taskController = taskController;
    this.eventController = eventController;
  }

  /**
   * Starts this controller
   */
  @Override
  public void run() {
    initButtons();
    initCreateComboBox();
    initListViews();
  }

  /**
   * Initializes basic conditions for a week
   */
  public void loadStartConditions() {
    this.loadSchedule();
    this.loadInitialTheme();
    this.loadSundayStart();
  }

  /**
   * Checks that the number of tasks is less than the max specified by user, displays error message
   * accordingly
   */
  public void checkMaxes() {
    int totalTasks = getTotalNumTasks();
    int maxTasksNum = this.getMaxNumTasks();

    int maxEventsNum = this.getMaxNumEvents();

    if (totalTasks > maxTasksNum) {
      taskMaxError.setText("MAX # OF TASKS EXCEEDED");
    } else {
      taskMaxError.setText("");
    }

    if (this.getTotalNumEvents() > maxEventsNum) {
      eventMaxError.setText("MAX # OF EVENTS EXCEEDED");
    } else {
      eventMaxError.setText("");
    }
  }

  /**
   * Initializes creation of buttons and setting their event actions
   */
  @FXML
  private void initButtons() {
    this.openFileButton.setOnAction(event -> mainStage.setScene(openFileScene));
    this.saveToFileButton.setOnAction(event -> weekData.save(sundayStart, weekName.getText(),
        currentTheme, Integer.parseInt(maxEvents.getText()), Integer.parseInt(maxTasks.getText())));
    this.shift.setOnAction(e -> this.handleSwitchWeekFormat());

    this.initThemeButton();
  }

  /**
   * Directs to options of creating either new Task or Event
   */
  @FXML
  private void initCreateComboBox() {
    MenuItem addEvent = new MenuItem("ADD EVENT");
    addEvent.setOnAction(event -> mainStage.setScene(createEventScene));
    create.getItems().add(addEvent);

    MenuItem addTask = new MenuItem("ADD TASK");
    addTask.setOnAction(event -> mainStage.setScene(createTaskScene));
    create.getItems().add(addTask);
  }


  /**
   * Initializes the options for choosing a theme
   */
  @FXML
  private void initThemeButton() {
    this.setTheme.getItems().clear();

    // Looping through the themes listed in the enumeration theme.java
    for (Theme theme : Theme.values()) {
      MenuItem themeButton = new MenuItem(theme.name());
      themeButton.setOnAction(e -> this.switchTheme(theme));
      this.setTheme.getItems().add(themeButton);
    }
  }

  /**
   * Initializes the maxes, checks the inputs and displays the most recent input
   */
  private void initMaxes() {
    maxEvents.setOnKeyReleased(event -> checkMaxes());
    maxTasks.setOnKeyReleased(event -> checkMaxes());
    int maxEventsNum = weekData.getCurrentBujoWeekData().weekData().maxEvents();
    maxEvents.setText(Integer.toString(maxEventsNum));
    int maxTasksNum = weekData.getCurrentBujoWeekData().weekData().maxTasks();
    maxTasks.setText(Integer.toString(maxTasksNum));
    this.checkMaxes();
  }

  /**
   * Sets the initial theme based on the last theme used/saved
   */
  @FXML
  private void loadInitialTheme() {
    Theme storedTheme = Theme.valueOf(weekData.getCurrentBujoWeekData().weekData().theme());
    this.switchTheme(storedTheme);
  }

  /**
   * Loads the selected option for starting on the week on Sunday or Monday
   */
  private void loadSundayStart() {
    this.sundayStart = !this.weekData.getCurrentBujoWeekData().weekData().sundayStart();
    this.handleSwitchWeekFormat();
  }

  /**
   * Loads the tasks into weekTaskList section
   */
  @FXML
  private void loadSchedule() {
    initMaxes();
    loadStats();
    loadTaskQueue();
    loadWeekdays();
    loadWeekName();
  }

  /**
   * Loads the task and events statistics for this week
   */
  private void loadStats() {
    int totalEvents = this.getTotalNumEvents();
    eventStats.setText("Total Events: " + totalEvents);

    int totalTasks = this.getTotalNumTasks();
    int totalCompleted = 0;
    for (TaskJson task : this.weekData.getTasks()) {
      if (task.completed()) {
        totalCompleted += 1;
      }
    }
    double percentageDone = (double) totalCompleted / (double) totalTasks;

    taskStats.setText("Total Tasks: " + totalTasks + " Percentage completed: " + percentageDone);
    taskProgressBar.setProgress(percentageDone);
  }

  /**
   * Loads the task queue for each day
   */
  private void loadTaskQueue() {
    weekTaskList.setItems(weekData.getTasks());
    weekTaskList.setCellFactory(new TaskCellFactory());
  }

  /**
   * Loads each week day with its respective tasks and events
   */
  private void loadWeekdays() {
    loadWeekdayTasks();
    loadWeekdayEvents();
  }

  /**
   * Loads the currently set week name
   */
  private void loadWeekName() {
    this.weekName.setText(this.weekData.getCurrentBujoWeekData().weekData().title());
  }

  /**
   * Loads the events for each week day
   */
  private void loadWeekdayEvents() {
    // Collecting the list of day events
    List<ListView<EventJson>> weekdayEvents = new ArrayList<>(
        Arrays.asList(sundayEventList, mondayEventList, tuesdayEventList, wednesdayEventList,
            thursdayEventList, fridayEventList, saturdayEventList)
    );

    // Getting the data and then setting them to their respective days
    for (int i = 0; i < weekdayEvents.size(); i++) {
      ObservableList<EventJson> dayEvents =
          weekData.getCurrentBujoWeekData().scheduleItems().get(i).getObservableEvents();
      weekdayEvents.get(i).setItems(dayEvents);
      weekdayEvents.get(i).setCellFactory(new EventCellFactory());
    }
  }

  /**
   * Loads the tasks for each week day
   */
  private void loadWeekdayTasks() {
    // Collecting the list of day tasks
    List<ListView<TaskJson>> weekdayTasks = new ArrayList<>(
        Arrays.asList(sundayTaskList, mondayTaskList, tuesdayTaskList, wednesdayTaskList,
            thursdayTaskList, fridayTaskList, saturdayTaskList)
    );

    // Getting the data and then setting them to their respective days
    for (int i = 0; i < weekdayTasks.size(); i++) {
      ObservableList<TaskJson> dayTasks =
          weekData.getCurrentBujoWeekData().scheduleItems().get(i).getObservableTasks();
      weekdayTasks.get(i).setItems(dayTasks);
      weekdayTasks.get(i).setCellFactory(new TaskCellFactory());
    }
  }

  /**
   * Switches to the different possible preloaded themes
   *
   * @param theme The theme to be used
   */
  private void switchTheme(Theme theme) {
    mainStage.getScene().getStylesheets().clear();

    switch (theme) {
      case GREEN -> mainStage.getScene().getStylesheets().add("StyleSheets/green.css");
      case BLUE -> mainStage.getScene().getStylesheets().add("StyleSheets/blue.css");
      case PINK -> mainStage.getScene().getStylesheets().add("StyleSheets/pink.css");
      case RESET -> this.mainStage.getScene().getStylesheets().clear();
      default -> throw new UnsupportedOperationException("OPTION NOT SUPPORTED");
    }

    this.currentTheme = theme.toString();
  }

  /**
   * Handles switching the format of the week. Between Sunday-Saturday and Monday-Sunday.
   */
  private void handleSwitchWeekFormat() {
    this.weekGrid.getChildren().remove(0, 7);

    if (this.sundayStart) {
      this.weekGrid.addColumn(0, this.monday);
      this.weekGrid.addColumn(1, this.tuesday);
      this.weekGrid.addColumn(2, this.wednesday);
      this.weekGrid.addColumn(3, this.thursday);
      this.weekGrid.addColumn(4, this.friday);
      this.weekGrid.addColumn(5, this.saturday);
      this.weekGrid.addColumn(6, this.sunday);
      this.sundayStart = false;
    } else {
      this.weekGrid.addColumn(0, this.sunday);
      this.weekGrid.addColumn(1, this.monday);
      this.weekGrid.addColumn(2, this.tuesday);
      this.weekGrid.addColumn(3, this.wednesday);
      this.weekGrid.addColumn(4, this.thursday);
      this.weekGrid.addColumn(5, this.friday);
      this.weekGrid.addColumn(6, this.saturday);
      this.sundayStart = true;
    }
  }

  /*
  Getters
   */

  /**
   * Getter for the total number of tasks that are currently on this week
   *
   * @return An int representing the # of tasks
   */
  public int getTotalNumTasks() {
    return weekData.getTasks().size();
  }

  /**
   * Getter for the total number of events that are currently on this week
   *
   * @return An int representing the # of events in this week
   */
  public int getTotalNumEvents() {
    int totalEvents = 0;
    for (int i = 0; i < weekData.getCurrentBujoWeekData().scheduleItems().size(); i++) {
      totalEvents += weekData
          .getCurrentBujoWeekData()
          .scheduleItems()
          .get(i)
          .getObservableEvents()
          .size();
    }
    return totalEvents;
  }

  /**
   * Getter for the maximum number of tasks that the user can input
   *
   * @return An int representing the max # of tasks
   */
  public int getMaxNumTasks() {
    try {
      return Integer.parseInt(maxTasks.getText());
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  /**
   * Returns the current theme.
   *
   * @return A string representing this theme
   */
  public String getCurrentTheme() {
    return this.currentTheme;
  }

  /**
   * Getter for this week's name
   *
   * @return The week's name
   */
  public String getWeekName() {
    return this.weekName.getText();
  }

  /**
   * Getter for the maximum number of events a user can have for this week
   *
   * @return An int representing the max # of events
   */
  public int getMaxNumEvents() {
    try {
      return Integer.parseInt(maxEvents.getText());
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  /**
   * initializes the list of events and tasks to have clickable events or tasks
   */
  public void initListViews() {
    List<ListView<TaskJson>> allWeekTasks = Arrays.asList(sundayTaskList, mondayTaskList,
        tuesdayTaskList, wednesdayTaskList, thursdayTaskList, fridayTaskList, saturdayTaskList);
    for (ListView<TaskJson> dayTasks : allWeekTasks) {
      dayTasks.setOnMouseClicked(
          event -> handleTaskClick(dayTasks.getSelectionModel().getSelectedItem(), dayTasks));
    }

    List<ListView<EventJson>> allWeekEvents = Arrays.asList(sundayEventList, mondayEventList,
        tuesdayEventList, wednesdayEventList, thursdayEventList, fridayEventList,
        saturdayEventList);
    for (ListView<EventJson> dayEvents : allWeekEvents) {
      dayEvents.setOnMouseClicked(
          event -> handleEventClick(dayEvents.getSelectionModel().getSelectedItem(), dayEvents));
    }

  }

  /**
   * Handles clicking on a task in the schedule
   *
   * @param selectedTask The specific task that the user clicked on
   * @param dayTaskList  The list of tasks for this day
   */
  public void handleTaskClick(TaskJson selectedTask, ListView<TaskJson> dayTaskList) {
    if (selectedTask != null) {
      mainStage.setScene(createTaskScene);
      dayTaskList.getItems().remove(selectedTask);
      taskController.setPrefills(selectedTask);
    }
  }

  /**
   * Handles clicking on an event in the schedule
   *
   * @param selectedEvent The event selected by the user to edit
   * @param dayEventList  The day of the event selected by the user
   */
  public void handleEventClick(EventJson selectedEvent, ListView<EventJson> dayEventList) {
    if (selectedEvent != null) {
      mainStage.setScene(createEventScene);
      dayEventList.getItems().remove(selectedEvent);
      eventController.setPrefills(selectedEvent);
    }
  }


  /**
   * Getter for whether this week starts on Sunday
   *
   * @return A boolean representing if this week starts at sunday
   */
  public boolean getSundayStart() {
    return this.sundayStart;
  }

  /**
   * Removes the given task from the schedule
   *
   * @param currTask task to be removed or deleted
   */
  public void removeScheduleTask(TaskJson currTask) {
    List<ListView<TaskJson>> allWeekTasks = Arrays.asList(sundayTaskList, mondayTaskList,
        tuesdayTaskList, wednesdayTaskList, thursdayTaskList, fridayTaskList, saturdayTaskList);
    for (ListView<TaskJson> dayTasks : allWeekTasks) {
      dayTasks.getItems().removeIf(task -> task.equals(currTask));
    }
  }

  /**
   * Removes the given event from the schedule
   *
   * @param currEvent event to be removed or deleted
   */
  public void removeScheduleEvent(EventJson currEvent) {
    List<ListView<EventJson>> allWeekEvents = Arrays.asList(sundayEventList, mondayEventList,
        tuesdayEventList, wednesdayEventList, thursdayEventList, fridayEventList,
        saturdayEventList);
    for (ListView<EventJson> dayEvents : allWeekEvents) {
      dayEvents.getItems().removeIf(event -> event.equals(currEvent));
    }
  }
}
