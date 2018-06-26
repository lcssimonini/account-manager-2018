package br.com.iftm.webserver.controller;

import br.com.iftm.dbserver.model.BankOperationsListTO;
import br.com.iftm.dbserver.model.api.PerformOperationsApi;
import br.com.iftm.webserver.service.PerformOperationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PerformOperationsController implements PerformOperationsApi {

    @Autowired
    private PerformOperationsService performOperationsService;

    @Override
    public void performOperations(@RequestBody BankOperationsListTO operationsListTO) {
        log.debug("iniciando a realização das operações: {}", operationsListTO);
        performOperationsService.perform(operationsListTO);
    }
}
