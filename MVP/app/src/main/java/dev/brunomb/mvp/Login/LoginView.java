package dev.brunomb.mvp.Login;

/**
 * Created by brunomiranda on 30/11/16.
 */

/**
 * Métodos obrigatórios na View LoginActivity, disponíveis para LoginPresenter
 */
public interface LoginView {

    /**
     * Métodos obrigatórios em View, disponíveis para Presenter
     *      Presenter -> View
     */
    interface LoginViewOps {
        void showError();
        void showSuccess();
        void showLoading();
        void hideLoading();
    }

    /**
     * operações oferecidas ao layer View para comunicação com Presenter
     *      View -> Presenter
     */
    interface LoginPresenterOps {
        void onConfigurationChanged(LoginViewOps view);
        void login(String email, String senha);
    }

    /**
     * operações oferecidas pelo layer Presenter para comunicações com Model
     *      Model -> Presenter
     */
    interface LoginRequiredPresenterOps {
        void onLoginSuccess();
        void onLoginError(String errorMessage);
        // qualquer operação de retorno Model -> Presenter
    }

    /**
     * operações oferecidos pelo layer Model para comunicações com Presenter
     *      Presenter -> Model
     */
    interface LoginModelOps {
        void doLogin(String email, String senha);
        // Qualquer operação referente à dados a ser chamado pelo Presenter
    }


}


