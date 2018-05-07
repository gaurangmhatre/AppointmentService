package io.swagger.model;

import com.cisco.appointmentservice.util.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Appointment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-05-06T20:07:41.326Z")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointment   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("host")
  private String host = null;

  @JsonProperty("from")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime from = null;

  @JsonProperty("to")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime to = null;

  @JsonProperty("participants")
  @Valid
  private List<String> participants = new ArrayList<String>();

  @JsonProperty("allowOverlap")
  private Boolean allowOverlap = false;

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

  public Appointment from(LocalDateTime from) {
    this.from = from;
    return this;
  }

  /**
   * Appointment start date and time
   * @return from
  **/
  @ApiModelProperty(example = "2018-05-03T10:15:30", required = true, value = "Appointment start date and time")
  @NotNull

  @Valid

  public LocalDateTime getFrom() {
    return from;
  }

  public void setFrom(LocalDateTime from) {
    this.from = from;
  }

  public Appointment to(LocalDateTime to) {
    this.to = to;
    return this;
  }

  /**
   * Appointment end date and time
   * @return to
  **/
  @ApiModelProperty(example = "2018-05-03T10:45:30", required = true, value = "Appointment end date and time")
  @NotNull

  @Valid

  public LocalDateTime getTo() {
    return to;
  }

  public void setTo(LocalDateTime to) {
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
   * Get participants
   * @return participants
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public List<String> getParticipants() {
    return participants;
  }

  public void setParticipants(List<String> participants) {
    this.participants = participants;
  }

  public Appointment allowOverlap(Boolean allowOverlap) {
    this.allowOverlap = allowOverlap;
    return this;
  }

  /**
   * Falg to allow participants to have overlapping appointments.
   * @return allowOverlap
  **/
  @ApiModelProperty(example = "false", value = "Falg to allow participants to have overlapping appointments.")


  public Boolean isAllowOverlap() {
    return allowOverlap;
  }

  public void setAllowOverlap(Boolean allowOverlap) {
    this.allowOverlap = allowOverlap;
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
        Objects.equals(this.participants, appointment.participants) &&
        Objects.equals(this.allowOverlap, appointment.allowOverlap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, host, from, to, participants, allowOverlap);
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
    sb.append("    allowOverlap: ").append(toIndentedString(allowOverlap)).append("\n");
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

