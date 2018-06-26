package br.com.iftm.dbserver.model.api;

import br.com.iftm.dbserver.model.BankOperationsListTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface PerformOperationsApi {

    @RequestMapping(value = "/perform", method = RequestMethod.POST)
    void performOperations(@RequestBody BankOperationsListTO operationsListTO);
}
