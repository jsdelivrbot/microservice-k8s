package com.epam.rulerunner.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User friendly information about validation errors
 */
@Validated

public class InlineResponse400   {
  @JsonProperty("code")
  private Integer code = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("classification")
  private String classification = null;

  @JsonProperty("additional")
  @Valid
  private List<InlineResponse400Additional> additional = null;

  public InlineResponse400 code(Integer code) {
    this.code = code;
    return this;
  }

  /**
   * Unique identifier of the occurred error
   * @return code
  **/
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public InlineResponse400 name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the validation error case
   * @return name
  **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public InlineResponse400 message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Short message of the error
   * @return message
  **/
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public InlineResponse400 description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Descriptive text explaining the details and background of the validation error
   * @return description
  **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public InlineResponse400 classification(String classification) {
    this.classification = classification;
    return this;
  }

  /**
   * Classification of the error in case the content holds classified information
   * @return classification
  **/
  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  public InlineResponse400 additional(List<InlineResponse400Additional> additional) {
    this.additional = additional;
    return this;
  }

  public InlineResponse400 addAdditionalItem(InlineResponse400Additional additionalItem) {
    if (this.additional == null) {
      this.additional = new ArrayList<>();
    }
    this.additional.add(additionalItem);
    return this;
  }

  /**
   * List of additional details that might provide information to client
   * @return additional
  **/
  @Valid

  public List<InlineResponse400Additional> getAdditional() {
    return additional;
  }

  public void setAdditional(List<InlineResponse400Additional> additional) {
    this.additional = additional;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse400 inlineResponse400 = (InlineResponse400) o;
    return Objects.equals(this.code, inlineResponse400.code) &&
        Objects.equals(this.name, inlineResponse400.name) &&
        Objects.equals(this.message, inlineResponse400.message) &&
        Objects.equals(this.description, inlineResponse400.description) &&
        Objects.equals(this.classification, inlineResponse400.classification) &&
        Objects.equals(this.additional, inlineResponse400.additional);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, name, message, description, classification, additional);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse400 {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    classification: ").append(toIndentedString(classification)).append("\n");
    sb.append("    additional: ").append(toIndentedString(additional)).append("\n");
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

