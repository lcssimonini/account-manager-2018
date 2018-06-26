package br.com.iftm.webserver.service;

import br.com.iftm.dbserver.model.BankOperationTO;
import br.com.iftm.dbserver.model.BankOperationsListTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
public class PerformOperationsService {

    private Queue<BankOperationTO> operationQueue = new ConcurrentLinkedQueue<>();
    private Set<Integer> lockedIds = new HashSet<>();

    public void perform(BankOperationsListTO operationsListTO) {
        queueOperations(operationsListTO);

        while(!operationQueue.isEmpty()) {
            BankOperationTO operation = operationQueue.poll();

        }
    }




    private void queueOperations(BankOperationsListTO operationsListTO) {
        operationQueue.addAll(operationsListTO.getOperations());
    }

    private class CallDatabaseThread extends Thread {

        BankOperationTO operation;

        CallDatabaseThread(BankOperationTO operation) {
            this.operation = operation;
        }

        @Override
        public void run() {

        }
    }
}
