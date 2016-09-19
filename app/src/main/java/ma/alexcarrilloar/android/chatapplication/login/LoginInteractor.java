package ma.alexcarrilloar.android.chatapplication.login;

/**
 * Created by alexcarrilloar on 9/18/16.
 */
public interface LoginInteractor {
    //Check session if a session exists
    void checkSession();

    //Handle SignUp
    void doSignUp(String email, String password);

    //Handle SignIn
    void doSignIn(String email, String password);

}
