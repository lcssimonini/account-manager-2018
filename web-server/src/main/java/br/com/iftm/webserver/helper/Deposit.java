package br.com.iftm.webserver.helper;

import br.com.iftm.dbserver.model.BankOperationTO;
import br.com.iftm.webserver.client.DatabaseClient;

public class Deposit implements Operationable {

    private BankOperationTO operation;

    public Deposit(BankOperationTO operation) {
        this.operation = operation;
    }

    @Override
    public void operate() {
        DatabaseClient client = new DatabaseClient();
        client.deposit(operation.getOriginAccountId(), operation.getAmmount());
    }
}
