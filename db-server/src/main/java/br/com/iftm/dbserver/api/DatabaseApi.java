package br.com.iftm.dbserver.api;

import br.com.iftm.dbserver.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface DatabaseApi {

    @PostMapping(value = "/{id}/deposit/{ammount}")
    Account deposit(@PathVariable("id") Integer id, @PathVariable("ammount") Long ammount);

    @PostMapping(value = "/{id}/withdraw/{ammount}")
    Account withdraw(@PathVariable("id") Integer id, @PathVariable("ammount") Long ammount);

    @GetMapping(value = "/{id}/balance")
    Account balance(@PathVariable("id") Integer id);
}
