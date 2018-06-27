package br.com.iftm.webserver.client;

import br.com.iftm.dbserver.model.Account;
import br.com.iftm.dbserver.model.BankOperation;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
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

    public Account deposit(Integer id, Double ammount) {

        return callDatabase(DEPOSIT, id, ammount);
    }

    public Account withdraw(Integer id, Double ammount) {

        return callDatabase(WITHDRAW, id, ammount);
    }

    public void transfer(Integer originId, Integer destinationId, Double ammount) {
        withdraw(originId, ammount);
        deposit(destinationId, ammount);
    }

    private Account callDatabase(BankOperation operation, Integer id, Double ammount) {
        HttpResponse<String> stringResponse = null;
        try {
            stringResponse = Unirest.post(getFullUrlPath(operation, id, ammount)).asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return gson.fromJson(stringResponse.getBody(), Account.class);
    }

    private String getFullUrlPath(BankOperation operation, Integer id, Double ammount) {
        String operationPath =  DEPOSIT.equals(operation) ? depositUrlTemplate : withdrawUrlTemplate;
        return resolveDatabaseUrl(id) + String.format(operationPath, id, ammount);
    }

    private String resolveDatabaseUrl(Integer id) {
        return DATABASE_01_URL;
    }

//    private String resolveDatabaseUrl(Integer id) {
//        return id <= 99 ? DATABASE_01_URL : DATABASE_02_URL;
//    }
}
