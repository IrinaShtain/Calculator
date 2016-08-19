package shtainyky.com.calculator.mail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import shtainyky.com.calculator.MainActivity;
import shtainyky.com.calculator.R;
import shtainyky.com.calculator.users.DatabaseHelper;

public class RecoveryPassword extends Activity {
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        helper = new DatabaseHelper(this);
        final EditText email = (EditText)findViewById(R.id.email_for_password);
        Button bt_send = (Button)findViewById(R.id.send_password);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("SendMailActivity", "Send Button Clicked.");
                String fromEmail = "your_email@gmail.com";  //please input your email
                String fromPassword = "your_password"; //please input your password for testing
                String toEmails = email.getText().toString();
                List<String> toEmailList = Arrays.asList(toEmails
                        .split("\\s*,\\s*"));
                Log.i("SendMailActivity", "To List: " + toEmailList);
                String emailSubject = "Recovering your login and password";
                String emailBody = helper.searchLoginAndPassword(toEmails);
                new SendMailTask(RecoveryPassword.this).execute(fromEmail,
                        fromPassword, toEmailList, emailSubject, emailBody);
                Toast.makeText(getApplicationContext(),
                        "Mail is sent. Please check your email and input login and password.",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RecoveryPassword.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
