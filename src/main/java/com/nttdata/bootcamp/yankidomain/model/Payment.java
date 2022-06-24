package com.nttdata.bootcamp.yankidomain.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Payment.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payments")
public class Payment {
  @Id
  private String id;
  @NotNull
  private String customerWalletId;
  @NotNull
  private String phone;
  @NotNull
  private String receiverWalletId;
  @NotNull
  private BigDecimal amount;

  @Override
  public String toString() {
    return "Payment{" +
        "customerWalletId='" + customerWalletId + '\'' +
        ", phone='" + phone + '\'' +
        ", receiverWalletId='" + receiverWalletId + '\'' +
        ", amount=" + amount +
        '}';
  }
}
