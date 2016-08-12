package shtainyky.com.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import shtainyky.com.calculator.users.DatabaseHelper;

public class MainActivity extends Activity implements View.OnClickListener{

    private EditText login, password;
    private TextView forgottenPass, registration;
    private Button btSend, loginFB, loginTwitter, loginGoogle;
    private DatabaseHelper helper = new DatabaseHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        initViews();
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId())
        {
            case R.id.btSend:
                String strLogin = login.getText().toString();
                String strPassword = password.getText().toString();

                if (TextUtils.isEmpty(strLogin) || TextUtils.isEmpty(strPassword))
                    return;

                String dbPassword = helper.searchPassword(strLogin);
                if (dbPassword.equals(strPassword)) {
                    intent = new Intent(MainActivity.this, DisplayCalculator.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Login and password don't match!", Toast.LENGTH_LONG).show();
                break;
            case R.id.loginFB:
                break;
            case R.id.loginTwitter:
                break;
            case R.id.loginGoogle:
                break;
            case R.id.forgotenPassword:
                intent = new Intent(MainActivity.this, RecoveryPassword.class);
                startActivity(intent);
                break;
            case R.id.registration:
                intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
                break;
        }

    }
    private void initViews()
    {
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);

        forgottenPass = (TextView) findViewById(R.id.forgotenPassword);
        registration = (TextView) findViewById(R.id.registration);
        forgottenPass.setOnClickListener(this);
        registration.setOnClickListener(this);

        btSend = (Button) findViewById(R.id.btSend);
        loginFB = (Button) findViewById(R.id.loginFB);
        loginTwitter = (Button) findViewById(R.id.loginTwitter);
        loginGoogle = (Button) findViewById(R.id.loginGoogle);

        btSend.setOnClickListener(this);
        loginFB.setOnClickListener(this);
        loginTwitter.setOnClickListener(this);
        loginGoogle.setOnClickListener(this);
    }
}
