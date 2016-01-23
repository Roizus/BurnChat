package es.dev_burnchat.burnchat;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        username=(EditText)findViewById(R.id.usernamefield);
        password=(EditText)findViewById(R.id.passwordfield);
        email=(EditText)findViewById(R.id.editText3);
    }

    public void onclickSignUp(View view){

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
    }
}
