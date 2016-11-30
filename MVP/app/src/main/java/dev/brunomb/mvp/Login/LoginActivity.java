package dev.brunomb.mvp.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import dev.brunomb.mvp.R;
import dev.brunomb.mvp.Utils.StateMaintainer;

public class LoginActivity extends AppCompatActivity implements LoginView.LoginViewOps {

    protected final String TAG = getClass().getSimpleName();
    private static final int ERROR = 0;
    private static final int SUCCESS = 1;
    private static final int SHOW_LOADING = 2;
    private static final int HIDE_LOADING = 3;

    // Responsável por manter estado dos objetos inscritos
    // durante mudanças de configuração
    private final StateMaintainer mStateMaintainer = new StateMaintainer(this.getFragmentManager(), TAG );

    private LoginPresenter mPresenter;

    EditText etEmailLogin;
    EditText etPasswordLogin;

    private Handler mainLooperHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case SHOW_LOADING:
                    progress.show();
                    break;
                case HIDE_LOADING:
                    progress.dismiss();
                    break;
                case SUCCESS:
                    Toast.makeText(mContext, "OK", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(mContext, "ERROR", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    private ProgressDialog progress;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMVPOps();
        setContentView(R.layout.activity_login);
        mContext = this;
        initViews();
    }

    private void initViews() {
        etEmailLogin = (EditText) findViewById(R.id.et_email_login);
        etPasswordLogin = (EditText) findViewById(R.id.et_password_login);
        Button btLogin = (Button) findViewById(R.id.bt_login);

        progress = new ProgressDialog(this, 0);
        progress.setMessage("Loading");
        progress.setTitle("Login");

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String email = etEmailLogin.getText().toString();
        String password = etPasswordLogin.getText().toString();

        mPresenter.login(email, password);
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
    }

    private void startMVPOps() {
        try {
            if ( mStateMaintainer.firstTimeIn() ) {
                Log.d(TAG, "onCreate() chamado pela primeira vez");
                initialize(this);
            } else {
                Log.d(TAG, "onCreate() chamado mais de uma vez");
                reinitialize(this);
            }
        } catch ( Exception e ) {
            Log.d(TAG, "onCreate() " + e );
            throw new RuntimeException( e );
        }
    }

    private void initialize(LoginView.LoginViewOps view ) throws InstantiationException, IllegalAccessException{
        mPresenter = new LoginPresenter(view);
        mStateMaintainer.put(LoginView.LoginPresenterOps.class.getSimpleName(), mPresenter);
    }

    private void reinitialize(LoginView.LoginViewOps view)
            throws InstantiationException, IllegalAccessException {
        mPresenter = mStateMaintainer.get(LoginView.LoginPresenterOps.class.getSimpleName());
        if ( mPresenter == null ) {
            Log.w(TAG, "recriando o Presenter");
            initialize(view);
        } else {
            mPresenter.onConfigurationChanged(view);
        }
    }

    @Override
    public void showError() {
        Message message = mainLooperHandler.obtainMessage(ERROR);
        message.sendToTarget();
    }

    @Override
    public void showSuccess() {
        Message message = mainLooperHandler.obtainMessage(SUCCESS);
        message.sendToTarget();
    }

    @Override
    public void showLoading() {
        Message message = mainLooperHandler.obtainMessage(SHOW_LOADING);
        message.sendToTarget();
    }

    @Override
    public void hideLoading() {
        Message message = mainLooperHandler.obtainMessage(HIDE_LOADING);
        message.sendToTarget();
    }
}
