package br.com.ifrm.webclient.controller;

import br.com.ifrm.webclient.service.OperationsService;
import br.com.iftm.dbserver.model.api.CreateOperationsApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateOperationsController implements CreateOperationsApi {

    private static final Logger log = Logger.getLogger(CreateOperationsController.class);

    @Autowired
    private OperationsService operationsService;

    @Override
    public void createOperations(@PathVariable("qtd") Integer qtd) {
        log.debug(String.format("requisição para criar %s operações", qtd));
        operationsService.performOperations(qtd);
    }
}
