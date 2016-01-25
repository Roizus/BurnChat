package es.dev_burnchat.burnchat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText email;
    MenuItem miActionProgressItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
       // getSupportActionBar().hide();

        username=(EditText)findViewById(R.id.usernamefield);
        password=(EditText)findViewById(R.id.passwordfield);
        email=(EditText)findViewById(R.id.editText3);
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



    public void hideProgressBar() {
        //Hide progress item.

        miActionProgressItem.setVisible(false);

    }
    public void onclickSignUp(View view){
        ParseUser user = new ParseUser();
        showProgressBar();
        String sName= String.valueOf(username.getText()).trim();
        String sPassword=String.valueOf(password.getText()).trim();
        String sEmail=String.valueOf(email.getText()).trim();

        Resources res=getResources();
        if(sName.isEmpty()){
            AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
            String message =
                    String.format(res.getString(R.string.empty_field_message), sName);
            builder.setMessage(message);
            builder.setTitle("Username field");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            AlertDialog dialog=builder.create();
            dialog.show();
        }else if(sPassword.isEmpty()){
            AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
            String message =
                    String.format(res.getString(R.string.empty_field_message),sPassword);
            builder.setMessage(message);
            builder.setTitle("Password field");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            AlertDialog dialog=builder.create();
            dialog.show();
        }else if(sEmail.isEmpty()){
            AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
            String message =
                    String.format(res.getString(R.string.empty_field_message),sEmail);
            builder.setMessage(message);
            builder.setTitle("e-mail field");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            AlertDialog dialog=builder.create();
            dialog.show();
        }
        else{
            user.setUsername(sName);
            user.setPassword(sPassword);
            user.setEmail(sEmail);
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
                        String message = "No se ha podido crear el usuario";
                        builder.setMessage(message);
                        builder.setTitle("Error SignUp");
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
