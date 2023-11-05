package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a single Day in a week with its list of tasks and events
 */
public final class DayJson {
  @JsonProperty("name")
  private final String name;
  @JsonProperty("events")
  private final List<EventJson> events;
  @JsonProperty("tasks")
  private final List<TaskJson> tasks;
  @JsonIgnore
  private final ObservableList<TaskJson> observableTasks;
  @JsonIgnore
  private final ObservableList<EventJson> observableEvents;

  /**
   * Default constructor for a DayJson
   *
   * @param name   The name of the day
   * @param events The events of this day
   * @param tasks  The tasks of this day
   */
  public DayJson(
      @JsonProperty("name") String name,
      @JsonProperty("events") List<EventJson> events,
      @JsonProperty("tasks") List<TaskJson> tasks
  ) {
    this.name = name;
    this.events = events;
    this.tasks = tasks;
    this.observableTasks = FXCollections.observableList(tasks);
    this.observableEvents = FXCollections.observableList(events);
  }

  /**
   * Getter for ObservableLists of EventJsons.
   *
   * @return ObservableList of EventJsons
   */
  public ObservableList<EventJson> getObservableEvents() {
    return observableEvents;
  }

  /**
   * Getter for the name of this day
   *
   * @return A String representing the day
   */
  @JsonProperty("name")
  public String name() {
    return name;
  }

  /**
   * Getter for the events of this day
   *
   * @return A List of EventJsons containing all event
   */
  @JsonProperty("events")
  public List<EventJson> events() {
    return events;
  }

  /**
   * Getter for the tasks of this day
   *
   * @return A List of TaskJsons containing all tasks
   */
  @JsonProperty("tasks")
  public List<TaskJson> tasks() {
    return tasks;
  }

  /**
   * Getter for an observable list of tasks. This helps our WeekDisplay to update whenever a
   * change occurs.
   *
   * @return An ObservableList of TaskJsons
   */
  public ObservableList<TaskJson> getObservableTasks() {
    return observableTasks;
  }
}
