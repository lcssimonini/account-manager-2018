package br.com.iftm.webserver.client;

import br.com.iftm.dbserver.model.BankOperation;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import static br.com.iftm.dbserver.model.BankOperation.DEPOSIT;
import static br.com.iftm.dbserver.model.BankOperation.WITHDRAW;

public class DatabaseClient {

    private Gson gson = new Gson();
    private static final String DATABASE_01_URL = "http://localhost:8082";

    private static final String DATABASE_02_URL = "http://localhost:8083";

    private static final String depositUrlTemplate = "/deposit/%s/%s";
    private static final String withdrawUrlTemplate = "/withdraw/%s/%s";

    public void deposit(Integer id, Double ammount) {
        callDatabase(DEPOSIT, id, ammount);
    }

    public void withdraw(Integer id, Double ammount) {
        callDatabase(WITHDRAW, id, ammount);
    }

    public void transfer(Integer originId, Integer destinationId, Double ammount) {
        withdraw(originId, ammount);
        deposit(destinationId, ammount);
    }

    private void callDatabase(BankOperation operation, Integer id, Double ammount) {
        try {
            Unirest.post(getFullUrlPath(operation, id, ammount)).asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private String getFullUrlPath(BankOperation operation, Integer id, Double ammount) {
        String operationPath =  DEPOSIT.equals(operation) ? depositUrlTemplate : withdrawUrlTemplate;
        return resolveDatabaseUrl(id) + String.format(operationPath, id, ammount);
    }

    private String resolveDatabaseUrl(Integer id) {
        return id <= 99 ? DATABASE_01_URL : DATABASE_02_URL;
    }
}
