package br.com.iftm.dbserver.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class BankOperationsListTO {

    @Builder.Default
    private List<BankOperationTO> operations = new ArrayList<>();

    public void addOperation(BankOperationTO operation) {
        this.operations.add(operation);
    }
}
