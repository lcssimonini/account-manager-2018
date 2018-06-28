package br.com.iftm.webserver.service;

import br.com.iftm.dbserver.model.BankOperationTO;
import br.com.iftm.dbserver.model.BankOperationsListTO;
import br.com.iftm.webserver.controller.PerformOperationsController;
import br.com.iftm.webserver.helper.CallDatabaseThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.Collections.synchronizedSet;

@Service
public class PerformOperationsService {

    private static final Logger log = Logger.getLogger(PerformOperationsService.class);

    private Queue<BankOperationTO> operationQueue = new ConcurrentLinkedQueue<>();
    private Set<Integer> lockedIds = synchronizedSet(new HashSet<>());

    public void perform(BankOperationsListTO operationsListTO) {
        queueOperations(operationsListTO);

        while(!operationQueue.isEmpty()) {
            BankOperationTO operation = operationQueue.poll();
            log.debug(String.format("tentativa de realizar a operação %s", operation));

            if (lockAccountIds(operation)) {
                new CallDatabaseThread(operation, lockedIds).start();
            } else {
                log.debug(String.format("não foi possível adquirir o lock para a operação %s", operation));
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
