package br.com.ifrm.webclient.client;

import br.com.iftm.dbserver.model.BankOperationsListTO;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class PerformOperationsClient {

    private Gson gson = new Gson();
    private static final String OPERATION_SERVER_URL = "http://localhost:8081/perform";

    public void callOperationsServer(BankOperationsListTO operationsListTO) {
        try {
            Unirest.post(OPERATION_SERVER_URL)
                    .header("Content-Type", "application/json")
                    .body(gson.toJson(operationsListTO))
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
