package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * test class for bujo json class
 */
class BujoJsonTest {

  private static BujoJson basicBujo;

  /**
   * initializing a bujo file with
   * 2 Monday events,
   * 1 Monday task,
   * 1 Tuesday event,
   * 2 Tuesday tasks
   */
  @BeforeAll
  public static void initBujo() {
    //MONDAY Events
    EventJson mondayMeeting = new EventJson("club meeting", "monday", "",
        "12:00", "1:00");
    EventJson mondayDinner = new EventJson("family dinner", "monday", "",
        "20:00", "21:00");
    List<EventJson> mondayEvents = Arrays.asList(mondayMeeting, mondayDinner);

    //MONDAY Tasks
    TaskJson mondayHomework = new TaskJson("homework", "monday", "calculus",
        false);
    List<TaskJson> mondayTasks = List.of(mondayHomework);

    //MONDAY
    DayJson monday = new DayJson("monday", mondayEvents, mondayTasks);


    //TUESDAY Events
    EventJson tuesdayClass = new EventJson("lecture", "tuesday", "",
        "12:00", "1:00");
    List<EventJson> tuesdayEvents = List.of(tuesdayClass);

    //TUESDAY Tasks
    TaskJson tuesdayHomework = new TaskJson("homework", "tuesday", "OOD",
        false);
    TaskJson tuesdayCooking = new TaskJson("cook dinner", "tuesday", "food",
        false);
    List<TaskJson> tuesdayTasks = Arrays.asList(tuesdayHomework, tuesdayCooking);

    //TUESDAY
    DayJson tuesday = new DayJson("tuesday", tuesdayEvents, tuesdayTasks);


    //Create schedule
    List<DayJson> listDays = Arrays.asList(monday, tuesday);
    WeekDataJson weekData = new WeekDataJson("WeekTest", "GREEN", 3,
        3, true);
    basicBujo = new BujoJson(weekData, listDays);


  }

  /**
   * testing the getObservableTasks method which should return all tasks in a bujo file
   */
  @Test
  public void testGetObservableTasks() {
    ObservableList<TaskJson> expectedTasks = FXCollections.observableArrayList();
    TaskJson mondayHomework = new TaskJson("homework", "monday", "calculus",
        false);
    TaskJson tuesdayHomework = new TaskJson("homework", "tuesday", "OOD",
        false);
    TaskJson tuesdayCooking = new TaskJson("cook dinner", "tuesday", "food",
        false);
    expectedTasks.add(mondayHomework);
    expectedTasks.add(tuesdayHomework);
    expectedTasks.add(tuesdayCooking);

    assertEquals(expectedTasks, basicBujo.getObservableTasks());

  }
}