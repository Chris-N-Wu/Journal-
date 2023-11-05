package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test class for the week data class
 */
class WeekDataTest {

  private static WeekData weekData;

  /**
   * initializing a new week data before each test
   */
  @BeforeEach
  public void resetWeekData() {
    weekData = new WeekData();

  }

  /**
   * testing whether a provided bujo file is acceptable
   */
  @Test
  public void testValidBujoFile() {
    Path goodBujo = Path.of("src/test/sample.bujo");
    assertTrue(WeekData.validBujoFile(goodBujo));

    Path badBujo = Path.of("src/test/nonexistant.bujo");
    assertFalse(WeekData.validBujoFile(badBujo));
  }

  /**
   * testing setting a given bujo file to the week data bujo field. Error thrown otherwise
   */
  @Test
  public void testSetCurrentWeekData() {
    Path goodBujo = Path.of("src/test/sample.bujo");
    weekData.setCurrentWeekData(goodBujo);

    Path badBujo = Path.of("src/test/nonexistant.bujo");
    IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> weekData.setCurrentWeekData(badBujo));
    String expectedException = "Must be given the path to a valid bujo file";
    assertEquals(expectedException, e.getMessage());
  }

  /**
   * testing retrieving all tasks from a bujo file field of this week data
   */
  @Test
  public void testGetTasks() {
    weekData.setCurrentWeekData(Path.of("src/test/testFile.bujo"));
    ObservableList<TaskJson> allTasks = FXCollections.observableArrayList();
    TaskJson task1 = new TaskJson("task 1 day 1", "Sunday", "task1",
        true);
    TaskJson task2 = new TaskJson("task 1 day 2", "Monday", "task2",
        true);
    TaskJson task3 = new TaskJson("task 1 day 3", "Tuesday", "task3",
        true);
    allTasks.add(task1);
    allTasks.add(task2);
    allTasks.add(task3);

    assertEquals(allTasks, weekData.getTasks());

  }

  /**
   * testing adding tasks to a bujo file field of this week data
   */
  @Test
  public void testAddTasks() {
    weekData.setCurrentWeekData(Path.of("src/test/testFile.bujo"));
    ObservableList<TaskJson> allTasks = FXCollections.observableArrayList();
    TaskJson task1 = new TaskJson("task 1 day 1", "Sunday", "task1",
        true);
    TaskJson task2 = new TaskJson("task 1 day 2", "Monday", "task2",
        true);
    TaskJson task3 = new TaskJson("task 1 day 3", "Tuesday", "task3",
        true);
    TaskJson task4 = new TaskJson("task 1 day 4", "Wednesday", "task4",
        false);
    allTasks.add(task1);
    allTasks.add(task2);
    allTasks.add(task3);
    allTasks.add(task4);

    weekData.addTask("task 1 day 4", 3, "task4",
        false);
    assertEquals(allTasks, weekData.getTasks());
  }

  /**
   * testing adding events to a bujo file field of this week data
   */
  @Test
  public void testAddEvents() {
    weekData.setCurrentWeekData(Path.of("src/test/testFile.bujo"));
    ObservableList<EventJson> allEvents = FXCollections.observableArrayList();
    EventJson event1 = new EventJson("event1", "Sunday", "event1",
        "04:00", "05:00");
    EventJson event2 = new EventJson("event2", "Monday", "event2",
        "06:00", "07:00");
    EventJson event3 = new EventJson("event3", "Tuesday", "event3",
        "08:00", "09:00");
    allEvents.add(event1);
    allEvents.add(event2);
    allEvents.add(event3);

    weekData.addEvent("event3", "08:00", "09:00",
        2, "event3");
    int allEventsCount =
        weekData.getCurrentBujoWeekData().scheduleItems().get(0).getObservableEvents().size()
            + weekData.getCurrentBujoWeekData().scheduleItems().get(1).getObservableEvents().size()
            + weekData.getCurrentBujoWeekData().scheduleItems().get(2).getObservableEvents().size();
    assertEquals(3, allEventsCount);
  }

  /**
   * testing the switching between ints 0 through 6 corresponding with each day of the week
   */
  @Test
  public void testIntToDow() {
    weekData.setCurrentWeekData(Path.of("src/test/testFile.bujo"));
    weekData.addEvent("event1", "08:00", "09:00",
        0, "event1");
    weekData.addEvent("event2", "08:00", "09:00",
        1, "event2");
    weekData.addEvent("event3", "08:00", "09:00",
        2, "event3");
    weekData.addEvent("event4", "08:00", "09:00",
        3, "event4");
    weekData.addEvent("event5", "08:00", "09:00",
        4, "event5");
    weekData.addEvent("event6", "08:00", "09:00",
        5, "event6");
    weekData.addEvent("event7", "08:00", "09:00",
        6, "event7");

    int allEventsCount =
        weekData.getCurrentBujoWeekData().scheduleItems().get(0).getObservableEvents().size()
            + weekData.getCurrentBujoWeekData().scheduleItems().get(1).getObservableEvents().size()
            + weekData.getCurrentBujoWeekData().scheduleItems().get(2).getObservableEvents().size();
    assertEquals(5, allEventsCount);

    IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class,
            () -> weekData.addEvent("event8", "08:00", "09:00",
                8, "event8"));
    String expectedException = "only 7 days in the week";
    assertEquals(expectedException, e.getMessage());

  }

  /**
   * testing whether the bujo file given has a password set or not
   */
  @Test
  public void testHasPassword() {
    weekData.setCurrentWeekData(Path.of("src/test/testFile.bujo"));
    assertFalse(weekData.hasPassword());
    weekData.setCurrentWeekData(Path.of("src/test/testFilePassword.bujo"));
    assertTrue(weekData.hasPassword());
  }

  /**
   * testing whether the given password matches the bujo file required password
   */
  @Test
  public void testValidatePassword() {
    weekData.setCurrentWeekData(Path.of("src/test/testFilePassword.bujo"));
    assertTrue(weekData.validatePassword("pass"));
    assertFalse(weekData.validatePassword("notPass"));
  }

  /**
   * testing whether saving to this week data's bujo file works
   */
  @Test
  public void testSave() {

    weekData.setCurrentWeekData(Path.of("src/test/testFile.bujo"));
    weekData.save(true, "week 1", "Green", 3, 3);

    assertEquals(3, weekData.getCurrentBujoWeekData().weekData().maxEvents());
    assertEquals(3, weekData.getCurrentBujoWeekData().weekData().maxTasks());
    assertTrue(weekData.getCurrentBujoWeekData().weekData().sundayStart());
    assertEquals("week 1", weekData.getCurrentBujoWeekData().weekData().title());
    assertEquals("Green", weekData.getCurrentBujoWeekData().weekData().theme());
  }

}