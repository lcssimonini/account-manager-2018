package br.com.iftm.dbserver.model.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CommandApi {

    @RequestMapping(value = "/operations/{qtd}", method = RequestMethod.POST)
    void createOperations(@PathVariable("qtd") Integer qtd);
}
