package br.com.iftm.dbserver.service;

import br.com.iftm.dbserver.model.Account;

public interface DatabaseService {

    Account deposit(Integer id, Double ammount);

    Account withdraw(Integer id, Double ammount);

    Account balance(Integer id);
}
