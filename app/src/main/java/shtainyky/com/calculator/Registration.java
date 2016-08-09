package shtainyky.com.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import shtainyky.com.calculator.Users.DatabaseHelper;
import shtainyky.com.calculator.Users.User;

public class Registration extends Activity implements View.OnClickListener {
    private EditText first_name, last_name, email, phone, child_first_name, child_birthday, login_reg, password_reg;
    private Button btSend;
    private DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        initViews();
    }
    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(string(first_name)) ||
                TextUtils.isEmpty(string(last_name)) ||
                TextUtils.isEmpty(string(email)) ||
                TextUtils.isEmpty(string(phone)) ||
                TextUtils.isEmpty(string(login_reg)) ||
                TextUtils.isEmpty(string(password_reg)))return;
        User user = new User();
        user.setFirst_name(string(first_name));
        user.setLast_name(string(last_name));
        user.setEmail(string(email));
        user.setPhone(string(phone));
        user.setChildren(string(child_first_name) + " " + string(child_birthday));
        user.setLogin(string(login_reg));
        user.setPassword(string(password_reg));
        helper.insertUser(user);
        Toast.makeText(getApplicationContext(), "Please enter your login and password!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Registration.this, MainActivity.class);
        startActivity(intent);
    }
    private void initViews() {
        first_name = (EditText)findViewById(R.id.first_name);
        last_name = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.email_for_registration);
        phone = (EditText)findViewById(R.id.phone_for_registration);
        child_first_name = (EditText)findViewById(R.id.child_first_name);
        child_birthday = (EditText)findViewById(R.id.child_birthday);
        login_reg = (EditText)findViewById(R.id.login_reg);
        password_reg = (EditText)findViewById(R.id.password_reg);

        btSend = (Button) findViewById(R.id.btSend_reg);
        btSend.setOnClickListener(this);
    }
    private String string(EditText view)
    {   if (TextUtils.isEmpty(view.getText().toString()))
            return null;
        return view.getText().toString();
    }


}
