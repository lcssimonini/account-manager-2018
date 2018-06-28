package br.com.iftm.webserver.helper;

import br.com.iftm.dbserver.model.BankOperationTO;
import br.com.iftm.webserver.client.DatabaseClient;

public class Transfer implements Operationable {

    private BankOperationTO operation;

    Transfer(BankOperationTO operation) {
        this.operation = operation;
    }

    @Override
    public void operate() {
        DatabaseClient client = new DatabaseClient();
        client.transfer(operation.getOriginAccountId(), operation.getDestinationAccountId(), operation.getAmmount());
    }
}
