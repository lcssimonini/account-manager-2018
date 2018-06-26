package br.com.iftm.dbserver.controller;

import br.com.iftm.dbserver.model.Account;
import br.com.iftm.dbserver.model.api.DatabaseApi;
import br.com.iftm.dbserver.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController implements DatabaseApi {

    @Autowired
    private DatabaseService service;

    @Override
    public Account deposit(@PathVariable("id") Integer id, @PathVariable("ammount") Double ammount) {
        return service.deposit(id, ammount);
    }

    @Override
    public Account withdraw(@PathVariable("id") Integer id, @PathVariable("ammount") Double ammount) {
        return service.withdraw(id, ammount);
    }

    @Override
    public Account balance(@PathVariable("id") Integer id) {
        return service.balance(id);
    }
}
