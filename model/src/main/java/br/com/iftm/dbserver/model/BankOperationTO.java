package br.com.iftm.dbserver.model;

import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class BankOperationTO {

    private BankOperation operation;
    private Integer originAccountId;
    private Integer destinationAccountId;
    private Double ammount;

    public void setDestinationAccountId(Integer accountId) {
        if (accountId.equals(originAccountId)) {
            destinationAccountId = accountId+1;
        }
    }
}
