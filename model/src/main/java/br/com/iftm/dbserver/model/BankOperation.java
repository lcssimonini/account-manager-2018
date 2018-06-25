package br.com.iftm.dbserver.model;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public enum BankOperation {

    DEPOSIT,
    WITHDRAW,
    TRANSFER;

    private static final List<BankOperation> operationValues = unmodifiableList(asList(values()));
    private static final int operationsSize = operationValues.size();
    private static final Random random = new Random();

    public static BankOperation getRandomOperation()  {
        return operationValues.get(random.nextInt(operationsSize));
    }
}
