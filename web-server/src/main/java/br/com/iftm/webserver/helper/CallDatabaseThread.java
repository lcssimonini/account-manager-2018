package br.com.iftm.webserver.helper;

import br.com.iftm.dbserver.model.BankOperationTO;
import br.com.iftm.webserver.service.PerformOperationsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.util.Set;

public class CallDatabaseThread extends Thread {

    private static final Logger log = Logger.getLogger(CallDatabaseThread.class);

    private BankOperationTO operation;
    private Set<Integer> lockedIds;

    public CallDatabaseThread(BankOperationTO operation, Set<Integer> lockedIds) {
        this.operation = operation;
        this.lockedIds = lockedIds;
    }

    @Override
    public void run() {
        log.debug(String.format("realizando a operação %s", operation));
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
