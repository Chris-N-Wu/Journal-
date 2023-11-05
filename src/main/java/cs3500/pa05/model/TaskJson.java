package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a task
 *
 * @param name The name of the task
 * @param dow The day of week of the task
 * @param description A description of the task
 * @param completed Whether the task has been completed
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("day-of-week") String dow,
    @JsonProperty("description") String description,
    @JsonProperty("completed") boolean completed
) {

}
