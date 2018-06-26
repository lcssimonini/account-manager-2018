package br.com.iftm.webserver.helper;

import br.com.iftm.dbserver.model.BankOperationTO;
import br.com.iftm.webserver.client.DatabaseClient;

public class Transfer implements Operationable {

    private DatabaseClient client = new DatabaseClient();

    @Override
    public void operate(BankOperationTO operation) {

    }
}
