package es.dev_burnchat.burnchat;



import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;

/*
 *
 * Jose Augusto Camacho Fernandez
 */

public class MyTestCase extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Button login;
    private EditText username;
    private EditText password;
    private static final String Login = "prueba";
    private static final String Password = "prueba";


    private LoginActivity actividad;

    public MyTestCase() {
        super(LoginActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

// Obtengo la activity actual
        actividad = getActivity();

// Instancio lo necesario (edittext y button)
        username = (EditText) actividad.findViewById(R.id.usernameField);
       password = (EditText) actividad.findViewById(R.id.passwordField);
        login = (Button)actividad.findViewById(R.id.LoginButton);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testAddValues() {

        Boolean UsuarioLogeado = false;


// Compruebo si el usuario esta logeado
        if (ParseUser.getCurrentUser()!=null) {
            ParseUser.logOut();
        }

// Meto prueba en el  Login
        TouchUtils.tapView(this, username);
        getInstrumentation().sendStringSync(Login);

// Meto prueba en el Password
        TouchUtils.tapView(this, password);
        getInstrumentation().sendStringSync(Password);

// Clikeo el boton de LogIn
        TouchUtils.clickView(this, login);

// Guardo el nombre de la actividad en la que me encuentro ahora
// y si ha ido bien deberia de llamarse "MainActivity"
        if (ParseUser.getCurrentUser()!=null) {
            UsuarioLogeado = true;
        }

// Comparo el nombre para ver si el resultado es el esperado
        assertTrue("Login result expect to be sucesfull and has been...", UsuarioLogeado);
    }
}

