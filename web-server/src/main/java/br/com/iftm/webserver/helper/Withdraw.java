package br.com.iftm.webserver.helper;

import br.com.iftm.dbserver.model.BankOperationTO;
import br.com.iftm.webserver.client.DatabaseClient;

public class Withdraw implements Operationable {

    private BankOperationTO operation;

    Withdraw(BankOperationTO operation) {
        this.operation = operation;
    }

    @Override
    public void operate() {
        DatabaseClient client = new DatabaseClient();
        client.withdraw(operation.getOriginAccountId(), operation.getAmmount());
    }
}
