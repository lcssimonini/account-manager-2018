package br.com.iftm.webserver.service;

import br.com.iftm.dbserver.model.BankOperationTO;
import br.com.iftm.dbserver.model.BankOperationsListTO;
import br.com.iftm.webserver.helper.CallDatabaseThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.Collections.synchronizedSet;

@Slf4j
@Service
public class PerformOperationsService {

    private Queue<BankOperationTO> operationQueue = new ConcurrentLinkedQueue<>();
    private Set<Integer> lockedIds = synchronizedSet(new HashSet<>());

    public void perform(BankOperationsListTO operationsListTO) {
        queueOperations(operationsListTO);

        while(!operationQueue.isEmpty()) {
            BankOperationTO operation = operationQueue.poll();
            log.debug("tentativa de realizar a operação {}", operation);

            if (lockAccountIds(operation)) {
                new CallDatabaseThread(operation, lockedIds).start();
            } else {
                log.debug("não foi possível adquirir o lock para a operação {}", operation);
                operationQueue.add(operation);
            }

        }
    }

    private boolean lockAccountIds(BankOperationTO operation) {
        return lockedIds.add(operation.getOriginAccountId()) && lockedIds.add(operation.getDestinationAccountId());
    }

    private void queueOperations(BankOperationsListTO operationsListTO) {
        operationQueue.addAll(operationsListTO.getOperations());
    }
}
