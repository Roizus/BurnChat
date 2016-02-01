package es.dev_burnchat.burnchat;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import com.parse.ParseUser;
import android.widget.Button;
import android.widget.EditText;




/**
 * Created by Vaio on 01/02/2016.
 */
public class MyTestCase extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Button login;
    private EditText user;
    private EditText pass;
    private static final String Login = "prueba";
    private static final String Password = "prueba";

    private LoginActivity loginAct;

    public MyTestCase() {
        super(LoginActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Obtengo la activity actual
        loginAct = getActivity();

        // Instancio lo necesario (edittext y button)
        user = (EditText) loginAct.findViewById(R.id.usernameField);
        pass = (EditText) loginAct.findViewById(R.id.passwordField);
        login = (Button)loginAct.findViewById(R.id.LoginButton);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testAddValues() {

        Boolean UsuarioLogeado = false;

        // Compruebo si el usuario esta logueado
        if (ParseUser.getCurrentUser()!=null) {
            ParseUser.logOut();
        }

        // Meto prueba en Login
        TouchUtils.tapView(this, user);
        getInstrumentation().sendStringSync(Login);

        // Meto prueba en Password
        TouchUtils.tapView(this, pass);
        getInstrumentation().sendStringSync(Password);

        // Clik en el boton de LogIn
        TouchUtils.clickView(this, login);

        // Guardo el nombre de la actividad en la que me encuentro ahora
        // y si ha ido bien deberia deberia ir "Main Activity"
        if (ParseUser.getCurrentUser()!=null) {
            UsuarioLogeado = true;
        }

        // Comparo el nombre para ver si el resultado es el idoneo
        assertTrue("Login result expect to be sucesfull and has been...", UsuarioLogeado);
    }
}

