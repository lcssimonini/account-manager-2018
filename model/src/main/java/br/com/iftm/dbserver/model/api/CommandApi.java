package br.com.iftm.dbserver.model.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface CommandApi {

    @PostMapping(value = "/operations/{qtd}")
    void createOperations(@PathVariable("qtd") Integer qtd);
}
