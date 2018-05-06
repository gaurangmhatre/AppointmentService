package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Appointment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-05-06T12:04:29.971Z")

public class Appointment   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("host")
  private String host = null;

  @JsonProperty("from")
  private String from = null;

  @JsonProperty("to")
  private String to = null;

  @JsonProperty("participants")
  @Valid
  private List<String> participants = new ArrayList<String>();

  public Appointment id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Appointment Id
   * @return id
  **/
  @ApiModelProperty(example = "20", value = "Appointment Id")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Appointment host(String host) {
    this.host = host;
    return this;
  }

  /**
   * Host's email address
   * @return host
  **/
  @ApiModelProperty(example = "sushantvairagade@outlook.com", required = true, value = "Host's email address")
  @NotNull


  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Appointment from(String from) {
    this.from = from;
    return this;
  }

  /**
   * Appointment start date and time
   * @return from
  **/
  @ApiModelProperty(example = "2018-05-03T10:15:30", required = true, value = "Appointment start date and time")
  @NotNull


  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public Appointment to(String to) {
    this.to = to;
    return this;
  }

  /**
   * Appointment end date and time
   * @return to
  **/
  @ApiModelProperty(example = "2018-05-03T10:45:30", required = true, value = "Appointment end date and time")
  @NotNull


  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public Appointment participants(List<String> participants) {
    this.participants = participants;
    return this;
  }

  public Appointment addParticipantsItem(String participantsItem) {
    this.participants.add(participantsItem);
    return this;
  }

  /**
   * List of participant's email address
   * @return participants
  **/
  @ApiModelProperty(example = "\"[sushantvairagade@outlook.com, doe@joe.com]\"", required = true, value = "List of participant's email address")
  @NotNull


  public List<String> getParticipants() {
    return participants;
  }

  public void setParticipants(List<String> participants) {
    this.participants = participants;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Appointment appointment = (Appointment) o;
    return Objects.equals(this.id, appointment.id) &&
        Objects.equals(this.host, appointment.host) &&
        Objects.equals(this.from, appointment.from) &&
        Objects.equals(this.to, appointment.to) &&
        Objects.equals(this.participants, appointment.participants);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, host, from, to, participants);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Appointment {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    participants: ").append(toIndentedString(participants)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

