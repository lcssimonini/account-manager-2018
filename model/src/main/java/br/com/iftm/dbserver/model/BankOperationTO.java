package br.com.iftm.dbserver.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
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

    public List<Integer> getOperationIds() {
        return Stream.of(originAccountId, destinationAccountId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void setDestinationAccountId(Integer accountId) {
        if (accountId.equals(originAccountId)) {
            destinationAccountId = accountId+1;
        } else {
            destinationAccountId = accountId;
        }
    }
}
