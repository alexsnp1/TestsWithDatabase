package api.iteration2_senior.requests.steps;

import api.iteration2_senior.models.DepositFundsRequest;
import api.iteration2_senior.requests.skeleton.requesters.CrudRequester;
import api.iteration2_senior.requests.skeleton.requesters.Endpoint;
import api.iteration2_senior.specs.RequestSpecs;
import api.iteration2_senior.specs.ResponseSpecs;

public class DepositFundsStep {
    public static void depositFunds(String authTokenUser, int userId, double balance) {
        DepositFundsRequest depositFundsRequest = DepositFundsRequest.builder()
                .id(userId).balance(balance).build();
        new CrudRequester(RequestSpecs.userAuthSpec(authTokenUser),
                Endpoint.ACCOUNTS_DEPOSIT,
                ResponseSpecs.returnsOK())
                .post(depositFundsRequest);
    }
}
