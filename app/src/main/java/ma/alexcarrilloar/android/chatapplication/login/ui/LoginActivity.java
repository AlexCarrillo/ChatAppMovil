package ma.alexcarrilloar.android.chatapplication.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ma.alexcarrilloar.android.chatapplication.R;
import ma.alexcarrilloar.android.chatapplication.contactlist.ui.ContactListActivity;
import ma.alexcarrilloar.android.chatapplication.login.LoginPresenter;
import ma.alexcarrilloar.android.chatapplication.login.LoginPresenterImpl;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.btnSignin)
    Button btnSignin;
    @Bind(R.id.btnSignup)
    Button btnSignup;
    @Bind(R.id.editTxtEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtPassword)
    EditText inputPassword;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticated();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.getDelegate().onDestroy();
        loginPresenter.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSignup)
    @Override
    public void handleSignUp() {
        if(!inputEmail.getText().toString().trim().equals("") && !inputPassword.getText().toString().trim().equals(""))
        {
            loginPresenter.registerNewUser(inputEmail.getText().toString(),
                    inputPassword.getText().toString());
        }
        else{
            loginError(getString(R.string.login_error_message_signup));
        }
    }

    @OnClick(R.id.btnSignin)
    @Override
    public void handleSignIn() {
        if(!inputEmail.getText().toString().trim().equals("") && !inputPassword.getText().toString().trim().equals("")) {
            loginPresenter.validateLogin(inputEmail.getText().toString(),
                    inputPassword.getText().toString());
        }
        else{

           loginError(getString(R.string.login_error_message_signin));
        }
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, ContactListActivity.class));
    }

    @Override
    public void loginError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin),error);
        inputPassword.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(layoutMainContainer,R.string.login_notice_message_signup,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup),error);
        inputPassword.setError(msgError);
    }

    private void setInputs(boolean enabled) {
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        btnSignin.setEnabled(enabled);
        btnSignup.setEnabled(enabled);
    }
}
