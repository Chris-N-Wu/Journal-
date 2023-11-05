package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single Week
 */
public final class WeekDataJson {
  @JsonProperty("week-title")
  private String title;
  @JsonProperty("theme")
  private String theme;
  @JsonProperty("max-events")
  private int maxEvents;
  @JsonProperty("max-tasks")
  private int maxTasks;
  @JsonProperty("sunday-start")
  private boolean sundayStart;
  @JsonProperty("password")
  private String password;

  /**
   * Default constructor for a week json
   *
   * @param title       The title for this week
   * @param theme       The theme for this week
   * @param maxEvents   The maximum number of events for this week
   * @param maxTasks    The maximum number of tasks for this week
   * @param sunDayStart True if the week starts on Sunday
   */
  public WeekDataJson(
      @JsonProperty("week-title") String title,
      @JsonProperty("theme") String theme,
      @JsonProperty("max-events") int maxEvents,
      @JsonProperty("max-tasks") int maxTasks,
      @JsonProperty("sunday-start") boolean sunDayStart
  ) {
    this.title = title;
    this.theme = theme;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.sundayStart = sunDayStart;
  }

  /**
   * Sets the maximum number of events for this week
   *
   * @param maxEvents The maximum # of events
   */
  public void setMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
  }

  /**
   * Setter for the max number of tasks in a week
   *
   * @param maxTasks The maximum # of tasks that the user desires
   */
  public void setMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
  }

  /**
   * Setter for the theme of the week
   *
   * @param theme The theme that the user desires
   */
  public void setTheme(String theme) {
    this.theme = theme;
  }

  /**
   * Setter for the title of this week
   *
   * @param title The title that the user desires
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Setter for dictating if the week display starts on Sunday or Monday
   *
   * @param sundayStart True if the week starts on sunday
   */
  public void setSundayStart(Boolean sundayStart) {
    this.sundayStart = sundayStart;
  }

  /**
   * Getter for this week's title
   *
   * @return A String representing this week's title
   */
  public String title() {
    return title;
  }

  /**
   * Getter for this week's theme
   *
   * @return A String representing this week's chosen theme
   */
  public String theme() {
    return theme;
  }

  /**
   * Getter for the maximum number of events for this week
   *
   * @return An integer representing the maximum # of events for this week
   */
  public int maxEvents() {
    return maxEvents;
  }

  /**
   * Getter for the maximum number of tasks for this week
   *
   * @return An integer representing the maximum # of tasks for this week
   */
  public int maxTasks() {
    return maxTasks;
  }

  /**
   * Getter for whether this week starts on Sunday
   *
   * @return A boolean representing if this week starts at sunday
   */
  public boolean sundayStart() {
    return sundayStart;
  }

  /**
   * Checks if this week contains a password (anything > length 0)
   *
   * @return True if this week has a password
   */
  public boolean hasPassword() {
    return !this.password.isEmpty();
  }

  /**
   * Checks if the inputted password is equal to this week's password
   *
   * @param password The user inputted password
   * @return True if the password is correct
   */
  public boolean validatePassword(String password) {
    return this.password.equals(password);
  }
}
