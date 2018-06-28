package br.com.iftm.dbserver.controller;

import br.com.iftm.dbserver.model.Account;
import br.com.iftm.dbserver.model.api.DatabaseApi;
import br.com.iftm.dbserver.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController implements DatabaseApi {

    private static final Logger log = Logger.getLogger(DatabaseController.class);

    @Autowired
    private DatabaseService service;

    @Override
    public Account deposit(@PathVariable("id") Integer id, @PathVariable("ammount") Double ammount) {
        log.debug(String.format("deposito de %s para a conta %s", ammount, id));
        return service.deposit(id, ammount);
    }

    @Override
    public Account withdraw(@PathVariable("id") Integer id, @PathVariable("ammount") Double ammount) {
        log.debug(String.format("saque de %s para a conta %s", ammount, id));
        return service.withdraw(id, ammount);
    }

    @Override
    public Account balance(@PathVariable("id") Integer id) {
        log.debug(String.format("informações da conta com id %s", id));
        return service.balance(id);
    }
}
