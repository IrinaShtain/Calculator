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

import shtainyky.com.calculator.integration.FB_Integration;
import shtainyky.com.calculator.integration.Google_Integration;
import shtainyky.com.calculator.integration.SocialMediaProvider;
import shtainyky.com.calculator.integration.Twitter_Integration;
import shtainyky.com.calculator.mail.RecoveryPassword;
import shtainyky.com.calculator.users.DatabaseHelper;

public class MainActivity extends Activity implements View.OnClickListener{

    private EditText login, password;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(this);
        setContentView(R.layout.activity_main_login);
        initViews();
    }

    @Override
    public void onClick(View view) {
       Intent intent;
        switch (view.getId())
        {   case R.id.btSend:
                loginIN();
                break;
            case R.id.loginFB:
                intent = new Intent(MainActivity.this, FB_Integration.class);
                startActivity(intent);
                finish();
                break;
            case R.id.loginTwitter:
                intent = new Intent(MainActivity.this, Twitter_Integration.class);
                startActivity(intent);
                finish();
                break;
            case R.id.loginGoogle:
                intent = new Intent(MainActivity.this, Google_Integration.class);
                startActivity(intent);
                finish();
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

    private void loginIN()
    {
        String strLogin = login.getText().toString();
        String strPassword = password.getText().toString();

        if (TextUtils.isEmpty(strLogin) || TextUtils.isEmpty(strPassword))
            return;

        String dbPassword = helper.searchPassword(strLogin);
        if (dbPassword.equals(strPassword)) {
            Intent intent = new Intent(MainActivity.this, DisplayCalculator.class);
            intent.putExtra(DisplayCalculator.INTEGRATION_WITH, SocialMediaProvider.REGISTRATION);
            startActivity(intent);
            finish();
        }
        else
            Toast.makeText(getApplicationContext(), "Login and password don't match!", Toast.LENGTH_LONG).show();
    }

    private void initViews()
    {
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);

        TextView forgottenPass = (TextView) findViewById(R.id.forgotenPassword);
        TextView registration = (TextView) findViewById(R.id.registration);
        forgottenPass.setOnClickListener(this);
        registration.setOnClickListener(this);

        Button btSend = (Button) findViewById(R.id.btSend);
        Button loginFB = (Button) findViewById(R.id.loginFB);
        Button loginTwitter = (Button) findViewById(R.id.loginTwitter);
        Button loginGoogle = (Button) findViewById(R.id.loginGoogle);

        btSend.setOnClickListener(this);
        loginFB.setOnClickListener(this);
        loginTwitter.setOnClickListener(this);
        loginGoogle.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
