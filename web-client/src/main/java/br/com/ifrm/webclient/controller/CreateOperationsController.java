package br.com.ifrm.webclient.controller;

import br.com.ifrm.webclient.service.OperationsService;
import br.com.iftm.dbserver.model.api.CreateOperationsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateOperationsController implements CreateOperationsApi {

    @Autowired
    private OperationsService operationsService;

    @Override
    public void createOperations(@PathVariable("qtd") Integer qtd) {
        operationsService.performOperations(qtd);
    }
}
