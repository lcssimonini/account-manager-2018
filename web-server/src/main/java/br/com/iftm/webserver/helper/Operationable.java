package br.com.iftm.webserver.helper;

import br.com.iftm.dbserver.model.BankOperationTO;

public interface Operationable {

    void operate(BankOperationTO operation);

}
