package br.com.iftm.dbserver.model.api;

import br.com.iftm.dbserver.model.BankOperationsListTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PerformOperationsApi {

    @PostMapping(value = "/perform")
    void createOperations(@RequestBody BankOperationsListTO operationsListTO);
}
