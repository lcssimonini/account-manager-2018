package br.com.iftm.webserver.controller;

import br.com.iftm.dbserver.model.BankOperationsListTO;
import br.com.iftm.dbserver.model.api.PerformOperationsApi;
import br.com.iftm.webserver.service.PerformOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerformOperationsController implements PerformOperationsApi {

    @Autowired
    PerformOperationsService performOperationsService;

    @Override
    public void performOperations(@RequestBody BankOperationsListTO operationsListTO) {
//        operationsListTO.getOperations().forEach(System.out::println);
        performOperationsService.perform(operationsListTO);
    }
}
