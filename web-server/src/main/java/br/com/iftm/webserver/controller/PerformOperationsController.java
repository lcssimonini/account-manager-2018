package br.com.iftm.webserver.controller;

import br.com.iftm.dbserver.model.BankOperationsListTO;
import br.com.iftm.dbserver.model.api.PerformOperationsApi;
import br.com.iftm.webserver.client.DatabaseClient;
import br.com.iftm.webserver.service.PerformOperationsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerformOperationsController implements PerformOperationsApi {

    private static final Logger log = Logger.getLogger(PerformOperationsController.class);

    @Autowired
    private PerformOperationsService performOperationsService;

    @Override
    public void performOperations(@RequestBody BankOperationsListTO operationsListTO) {
        log.debug(String.format("iniciando a realização das operações: %s", operationsListTO));
        performOperationsService.perform(operationsListTO);
    }
}
