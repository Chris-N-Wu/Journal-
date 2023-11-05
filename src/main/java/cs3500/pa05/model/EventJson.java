package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an event
 *
 * @param name        Name of the event
 * @param dow         Day of week
 * @param description A description of the event
 * @param startTime   The start date of the event
 * @param endTime     The end date of the event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("day-of-week") String dow,
    @JsonProperty("description") String description,
    @JsonProperty("start-time") String startTime,
    @JsonProperty("end-time") String endTime
) {

}
