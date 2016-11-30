package dev.brunomb.mvp.Login;

import android.os.Handler;
import dev.brunomb.mvp.Model.Central;
import dev.brunomb.mvp.Model.CentralApi;
import dev.brunomb.mvp.Utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by brunomiranda on 30/11/16.
 */

public class LoginModel implements LoginView.LoginModelOps, Callback<Central> {

    private LoginView.LoginRequiredPresenterOps mPresenter;
    private CentralApi apiService;

    @Override
    public void doLogin(String email, String senha) {
        Call<Central> call = apiService.LorenIpsum(email);
        call.enqueue(this);
    }

    LoginModel(LoginView.LoginRequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
        initApiComponents();
    }

    private void initApiComponents() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL_TWO)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(CentralApi.class);
    }

    @Override
    public void onResponse(Call<Central> call, Response<Central> response) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.onLoginSuccess();
            }
        }, 6000);
    }

    @Override
    public void onFailure(Call<Central> call, Throwable t) {
        mPresenter.onLoginError(t.getMessage());
    }
}
