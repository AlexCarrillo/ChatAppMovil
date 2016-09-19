package ma.alexcarrilloar.android.chatapplication.login;

/**
 * Created by alexcarrilloar on 9/18/16.
 */
public interface LoginRepository {

    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();
}
