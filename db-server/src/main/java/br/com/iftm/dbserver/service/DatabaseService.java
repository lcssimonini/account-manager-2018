package br.com.iftm.dbserver.service;

import br.com.iftm.dbserver.model.Account;

public interface DatabaseService {

    Account deposit(Integer id, Long ammount);

    Account withdraw(Integer id, Long ammount);

    Account balance(Integer id);
}
