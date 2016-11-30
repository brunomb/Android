package dev.brunomb.mvp.Login;

import java.lang.ref.WeakReference;

/**
 * Created by brunomiranda on 30/11/16.
 */
public class LoginPresenter implements LoginView.LoginPresenterOps, LoginView.LoginRequiredPresenterOps {

    private WeakReference<LoginView.LoginViewOps> mView;
    private LoginView.LoginModelOps mModel;

    private static final int ERROR = 0;
    private static final int SUCCESS = 1;
    private static final int SHOW_LOADING = 2;
    private static final int HIDE_LOADING = 3;
    private static final int IDLE = 4;

    private int state = IDLE;

    LoginPresenter(LoginView.LoginViewOps mView) {
        this.mView = new WeakReference<>(mView);
        this.mModel = new LoginModel(this);
    }

    @Override
    public void onConfigurationChanged(LoginView.LoginViewOps view) {
        mView = new WeakReference<>(view);

        switch (state) {
            case SHOW_LOADING:
                mView.get().showLoading();
                break;
            case HIDE_LOADING:
                mView.get().hideLoading();
                break;
            case SUCCESS:
                mView.get().showSuccess();
                break;
            case ERROR:
                mView.get().showError();
                break;
            case IDLE:
                break;
            default:
                break;
        }

    }

    @Override
    public void login(String email, String senha) {
        state = SHOW_LOADING;
        mModel.doLogin(email, senha);
        mView.get().showLoading();
    }

    @Override
    public void onLoginSuccess() {
        state = HIDE_LOADING;
        mView.get().hideLoading();
        state = SUCCESS;
        mView.get().showSuccess();
    }

    @Override
    public void onLoginError(String errorMessage) {
        state = ERROR;
        mView.get().showError();
    }
}
