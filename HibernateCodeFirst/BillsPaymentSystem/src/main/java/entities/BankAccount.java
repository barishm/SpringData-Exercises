package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity(name = "bank_account")
public class BankAccount extends BillingDetail{
    @Enumerated(EnumType.STRING)
    private final BillingType billingType;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "swift_code", nullable = false)
    private String swiftCode;

    public BankAccount() {
        this.billingType = BillingType.BANK_ACCOUNT;
    }

    public BankAccount(String bankName, String swiftCode) {
        this.bankName = bankName;
        this.swiftCode = swiftCode;
        this.billingType = BillingType.BANK_ACCOUNT;
    }
}
