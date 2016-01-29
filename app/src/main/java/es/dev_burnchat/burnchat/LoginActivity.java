package es.dev_burnchat.burnchat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    TextView mSignupTextView;
    EditText username;
    EditText password;
    MenuItem miActionProgressItem;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       getSupportActionBar().hide();
        mSignupTextView=(TextView)findViewById(R.id.SignupButton);
        mSignupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });

        username=(EditText)findViewById(R.id.usernameField);
        password=(EditText)findViewById(R.id.passwordField);


        //añadimos el splash_screen

        TextView myTitle = (TextView)findViewById(R.id.title);
        TextView mySubtitle = (TextView)findViewById(R.id.subtitle);
        Typeface myFont = Typeface.createFromAsset(getAssets(),"font/leadcoat.ttf");
        myTitle.setTypeface(myFont);
        mySubtitle.setTypeface(myFont);


        //añadir movimiento al titulo
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        myTitle.startAnimation(shake);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_activity,menu);
        return true ;
    }



    // Para iniciarlo, añadiremos el método onPrepareOptionsMenu
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
// Store instance of the menu item containing progress
        miActionProgressItem = menu.findItem(R.id.miActionProgress);
// Extract the action-view from the menu item
        ProgressBar v = (ProgressBar) MenuItemCompat.getActionView(miActionProgressItem);
// Return to finish
        return super.onPrepareOptionsMenu(menu);
    }


    public void showProgressBar(){
        //Show progress item.
        miActionProgressItem.setVisible(true);
    }


//
    public void hideProgressBar() {
        //Hide progress item.

        miActionProgressItem.setVisible(false);

    }


    public void onclickLogin(View view){
        getSupportActionBar().show();
        showProgressBar();
        String sName= String.valueOf(username.getText()).trim();
        String sPassword=String.valueOf(password.getText()).trim();


        Resources res=getResources();
        if(sName.isEmpty()){
            AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
            String message =
                    String.format(res.getString(R.string.empty_field_message), sName);
            builder.setMessage(message);
            builder.setTitle("Username field");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            AlertDialog dialog=builder.create();
            dialog.show();

        }else if(sPassword.isEmpty()){
            AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
            String message =
                    String.format(res.getString(R.string.empty_field_message),sPassword);
            builder.setMessage(message);
            builder.setTitle("Password field");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            AlertDialog dialog=builder.create();
            dialog.show();


        }
        else{
            ParseUser.logInInBackground(sName, sPassword, new LogInCallback() {

                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                        String message = "No se ha podido loguear el usuario";
                        builder.setMessage(message);
                        builder.setTitle("Error Login");
                        builder.setPositiveButton(android.R.string.ok, null);
                        builder.setIcon(android.R.drawable.ic_dialog_alert);
                        AlertDialog dialog=builder.create();
                        dialog.show();
                    }
                    hideProgressBar();

                }
            });


        }

    }

}
