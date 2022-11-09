package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum BillingType {
    CREDIT_CARD("credit card"), BANK_ACCOUNT("bank account");
    private String value;



    BillingType(String value) {
        this.value = value;
    }
}
