package shtainyky.com.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    EditText login, password;
    TextView forgottenPass, registration;
    Button btSend, loginFB, loginTwitter, loginGoogle;


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
                intent = new Intent(MainActivity.this, DisplayCalculator.class);
                startActivity(intent);
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
