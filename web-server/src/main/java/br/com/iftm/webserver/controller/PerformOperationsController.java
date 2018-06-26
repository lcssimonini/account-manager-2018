package br.com.iftm.webserver.controller;

import br.com.iftm.dbserver.model.BankOperationsListTO;
import br.com.iftm.dbserver.model.api.PerformOperationsApi;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerformOperationsController implements PerformOperationsApi {

    @Override
    public void performOperations(@RequestBody BankOperationsListTO operationsListTO) {
        operationsListTO.getOperations().forEach(System.out::println);
    }
}
