package br.com.ifrm.webclient.service;

import br.com.iftm.dbserver.model.BankOperationsListTO;

public interface OperationsService {

    BankOperationsListTO createRandomOperations(Integer qtd);
}
