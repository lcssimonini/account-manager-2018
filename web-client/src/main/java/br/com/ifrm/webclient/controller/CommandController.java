package br.com.ifrm.webclient.controller;

import br.com.ifrm.webclient.service.OperationsService;
import br.com.iftm.dbserver.model.api.CommandApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController implements CommandApi {

    @Autowired
    private OperationsService operationsService;

    @Override
    public void createOperations(Integer qtd) {
        operationsService.performOperations(qtd);
    }
}
