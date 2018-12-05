package com.epam.rulerunner.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Order attributes that might be modified
 */
@Validated
public class Order {

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("account")
  private String account = null;

  @JsonProperty("amount")
  private Integer amount = null;

  @JsonProperty("ccy")
  private String ccy = null;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @NotNull
  @Valid
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @NotNull
  @Valid
  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  @NotNull
  @Valid
  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  @NotNull
  @Valid
  public String getCcy() {
    return ccy;
  }

  public void setCcy(String ccy) {
    this.ccy = ccy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Objects.equals(id, order.id) &&
            Objects.equals(type, order.type) &&
            Objects.equals(account, order.account) &&
            Objects.equals(amount, order.amount) &&
            Objects.equals(ccy, order.ccy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, account, amount, ccy);
  }

  @Override
  public String toString() {
    return "Order{" +
            "id='" + id + '\'' +
            ", type='" + type + '\'' +
            ", account='" + account + '\'' +
            ", amount=" + amount +
            ", ccy='" + ccy + '\'' +
            '}';
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

