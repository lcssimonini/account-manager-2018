package br.com.iftm.webserver.client;

import br.com.iftm.dbserver.model.Account;
import br.com.iftm.dbserver.model.BankOperation;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;

import java.util.Optional;

import static br.com.iftm.dbserver.model.BankOperation.DEPOSIT;
import static br.com.iftm.dbserver.model.BankOperation.WITHDRAW;

public class DatabaseClient {

    private static final Logger log = Logger.getLogger(DatabaseClient.class);

    private Gson gson = new Gson();
    private static final String DATABASE_01_URL = "http://localhost:8082";

    private static final String DATABASE_02_URL = "http://localhost:8083";

    private static final String depositUrlTemplate = "/deposit/%s/%s";
    private static final String withdrawUrlTemplate = "/withdraw/%s/%s";

    public Account deposit(Integer id, Double ammount) {
        log.debug(String.format("chamar banco de dados para depositar %s na conta com id %s", ammount, id));
        return callDatabase(DEPOSIT, id, ammount);
    }

    public Account withdraw(Integer id, Double ammount) {
        log.debug(String.format("chamar banco de dados para sacar %s na conta com id %s", ammount, id));
        return callDatabase(WITHDRAW, id, ammount);
    }

    public void transfer(Integer originId, Integer destinationId, Double ammount) {
        log.debug(String.format("chamar banco de dados para transferir %s da conta %s para a conta %s", ammount, originId, destinationId));
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

        return Optional.ofNullable(stringResponse)
                .map(a->gson.fromJson(a.getBody(), Account.class))
                .orElse(Account.builder().build()) ;
    }

    private String getFullUrlPath(BankOperation operation, Integer id, Double ammount) {
        String operationPath =  DEPOSIT.equals(operation) ? depositUrlTemplate : withdrawUrlTemplate;
        return resolveDatabaseUrl(id) + String.format(operationPath, resolveId(id), ammount);
    }

    private Integer resolveId(Integer id) {
        return id <= 99 ? id : id-100;
    }

    private String resolveDatabaseUrl(Integer id) {
        return id <= 99 ? DATABASE_01_URL : DATABASE_02_URL;
    }
}
