package com.epam.rulerunner.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Field information in order to identify the cause of validation error
 */
@Validated

public class InlineResponse400Additional   {
  @JsonProperty("field")
  private String field = null;

  @JsonProperty("message")
  private String message = null;

  public InlineResponse400Additional field(String field) {
    this.field = field;
    return this;
  }

  /**
   * The name of the field that caused validation error
   * @return field
  **/
  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public InlineResponse400Additional message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Validation error message that belongs to this field only
   * @return message
  **/
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse400Additional inlineResponse400Additional = (InlineResponse400Additional) o;
    return Objects.equals(this.field, inlineResponse400Additional.field) &&
        Objects.equals(this.message, inlineResponse400Additional.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse400Additional {\n");
    
    sb.append("    field: ").append(toIndentedString(field)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

