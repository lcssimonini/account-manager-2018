package br.com.iftm.dbserver.model.api;

import br.com.iftm.dbserver.model.Account;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface DatabaseApi {

    @RequestMapping(value = "/deposit/{id}/{ammount}", method = RequestMethod.POST)
    Account deposit(@PathVariable("id") Integer id, @PathVariable("ammount") Double ammount);

    @RequestMapping(value = "/withdraw/{id}/{ammount}", method = RequestMethod.POST)
    Account withdraw(@PathVariable("id") Integer id, @PathVariable("ammount") Double ammount);

    @RequestMapping(value = "/balance/{id}", method = RequestMethod.GET)
    Account balance(@PathVariable("id") Integer id);
}
