package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Represents a Json object that is the base for the bullet journaling app
 *
 * @param weekData      The base data for this week; such as week name, theme preferences, etc.
 * @param scheduleItems The items, tasks and events, for this week
 */
public record BujoJson(
    @JsonProperty("week-data") WeekDataJson weekData,
    @JsonProperty("schedule-items") List<DayJson> scheduleItems
) {

  /**
   * Getter for an observable list of tasks. This is used so that whenever a task is updated,
   * the list is updated as well
   *
   * @return An ObservableList of TaskJsons
   */
  @JsonIgnore
  public ObservableList<TaskJson> getObservableTasks() {

    ObservableList<TaskJson> tasks = FXCollections.observableArrayList();
    List<ObservableList<TaskJson>> tasksListOfList = new ArrayList<>();
    for (DayJson dayJson : scheduleItems) {
      tasksListOfList.add(dayJson.getObservableTasks());
    }

    for (ObservableList<TaskJson> tasksList : tasksListOfList) {
      tasks.addAll(tasksList);
      tasksList.addListener(
          (ListChangeListener<TaskJson>) c -> updateWeeklyOverview(tasks, tasksListOfList));
    }
    return tasks;
  }

  /**
   * Updates the weekly overview list of tasks
   *
   * @param weeklyOverview     The weekly overview list.
   * @param listOfAllTaskLists A list of TaskJsons from each day.
   */
  private void updateWeeklyOverview(ObservableList<TaskJson> weeklyOverview,
                                    List<ObservableList<TaskJson>> listOfAllTaskLists) {
    weeklyOverview.clear();
    for (ObservableList<TaskJson> list : listOfAllTaskLists) {
      weeklyOverview.addAll(list);
    }
  }
}
