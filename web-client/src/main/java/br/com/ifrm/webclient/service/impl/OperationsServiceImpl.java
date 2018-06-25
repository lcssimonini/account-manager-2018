package br.com.ifrm.webclient.service.impl;

import br.com.ifrm.webclient.service.OperationsService;
import br.com.iftm.dbserver.model.BankOperationTO;
import br.com.iftm.dbserver.model.BankOperationsListTO;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Service;

import static br.com.iftm.dbserver.model.BankOperation.getRandomOperation;

@Service
public class OperationsServiceImpl implements OperationsService {

    @Override
    public BankOperationsListTO createRandomOperations(Integer qtd) {
        BankOperationsListTO operationList = BankOperationsListTO.builder().build();

        for (int i = 0; i <= qtd; i++) {
            operationList.addOperation(BankOperationTO.builder()
                    .operation(getRandomOperation())
                    .originAccountId(getRandomAccountId())
                    .destinationAccountId(getRandomAccountId())
                    .ammount(getRandomAmmount())
                    .build());
        }

        return operationList;
    }

    private Double getRandomAmmount() {
        double leftLimit = 0D;
        double rightLimit = 10000D;
        return new RandomDataGenerator().nextUniform(leftLimit, rightLimit);
    }

    private Integer getRandomAccountId() {
        int leftLimit = 0;
        int rightLimit = 199;
        return new RandomDataGenerator().nextInt(leftLimit, rightLimit);
    }
}
