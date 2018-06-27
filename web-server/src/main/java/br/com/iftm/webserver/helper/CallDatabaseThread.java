package br.com.iftm.webserver.helper;

import br.com.iftm.dbserver.model.BankOperationTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class CallDatabaseThread extends Thread {

    private BankOperationTO operation;
    private Set<Integer> lockedIds;

    public CallDatabaseThread(BankOperationTO operation, Set<Integer> lockedIds) {
        this.operation = operation;
        this.lockedIds = lockedIds;
    }

    @Override
    public void run() {
        log.debug("realizando a operação {}", operation);
        resolveOperation().operate();
        lockedIds.removeAll(operation.getOperationIds());
    }

    private Operationable resolveOperation() {
        if (operation.isDeposit()) {
            return new Deposit(operation);
        } else if (operation.isWithdraw()) {
            return new Withdraw(operation);
        } else {
            return new Transfer(operation);
        }
    }
}
