package dev.brunomb.mvp.Model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by brunomiranda on 18/11/16.
 */

public interface CentralApi {
    //TODO change before commit
    @POST("/some_endpoint/{x-x}")
    Call<Central> LorenIpsum(@Path("x-x") String serial);

    @GET("/getHealth")
    Call<String> checkHealth();

    @POST("/new_account")
    Call<Account.CreateAccountResponse> newAccount(@Body Account email);

}
