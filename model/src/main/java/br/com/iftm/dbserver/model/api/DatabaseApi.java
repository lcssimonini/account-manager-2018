package br.com.iftm.dbserver.model.api;

import br.com.iftm.dbserver.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface DatabaseApi {

    @PostMapping(value = "/deposit/{id}/{ammount}")
    Account deposit(@PathVariable("id") Integer id, @PathVariable("ammount") Double ammount);

    @PostMapping(value = "/withdraw/{id}/{ammount}")
    Account withdraw(@PathVariable("id") Integer id, @PathVariable("ammount") Double ammount);

    @GetMapping(value = "/balance/{id}")
    Account balance(@PathVariable("id") Integer id);
}
