package shtainyky.com.calculator;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import shtainyky.com.calculator.Users.ChildFragment;
import shtainyky.com.calculator.Users.DatabaseHelper;
import shtainyky.com.calculator.Users.User;

public class Registration extends Activity implements View.OnClickListener {
    private EditText first_name, last_name, email, phone, child_first_name, child_birthday, login_reg, password_reg;
    private DatabaseHelper helper = new DatabaseHelper(this);
    FloatingActionButton fab;
    private String children = " ";
    private List<ChildFragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        initViews();
    }
    @Override
    public void onClick(View view) {
        //if user don't input all fields
        if (TextUtils.isEmpty(first_name.getText().toString()) ||
                TextUtils.isEmpty(last_name.getText().toString()) ||
                TextUtils.isEmpty(email.getText().toString()) ||
                TextUtils.isEmpty(phone.getText().toString()) ||
                TextUtils.isEmpty(login_reg.getText().toString()) ||
                TextUtils.isEmpty(password_reg.getText().toString()))return;
        //if user has one child
        if (!TextUtils.isEmpty(child_first_name.getText().toString()))
            children = children + string(child_first_name) + " ";
        if (!TextUtils.isEmpty(child_birthday.getText().toString()))
            children = children + string(child_birthday) + " ";
        //if user has more children
        if (list.size() != 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                String name = list.get(i).getNameChild().getText().toString();
                String bday = list.get(i).getBdayChild().getText().toString();
                children = children + name + " " + bday + " ";
            }
        }
        //init user
        User user = new User();
        user.setFirst_name(string(first_name));
        user.setLast_name(string(last_name));
        user.setEmail(string(email));
        user.setPhone(string(phone));
        user.setChildren(children);
        user.setLogin(string(login_reg));
        user.setPassword(string(password_reg));
        helper.insertUser(user);
        Toast.makeText(getApplicationContext(), "Please enter your login and password!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Registration.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Please enter your login and password!", Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }

    private void initViews() {
        list = new ArrayList<>();
        first_name = (EditText)findViewById(R.id.first_name);
        last_name = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.email_for_registration);
        phone = (EditText)findViewById(R.id.phone_for_registration);
        child_first_name = (EditText)findViewById(R.id.child_first_name);
        child_birthday = (EditText)findViewById(R.id.child_birthday);
        login_reg = (EditText)findViewById(R.id.login_reg);
        password_reg = (EditText)findViewById(R.id.password_reg);

        Button btSend = (Button) findViewById(R.id.btSend_reg);
        btSend.setOnClickListener(this);

        fab = (FloatingActionButton)findViewById(R.id.fab);
    }
    private String string(EditText view)
    {
        return view.getText().toString();
    }
    //FloatingActionButton is clicked
    public  void onFabClick(View view)
    {
        if (TextUtils.isEmpty(child_first_name.getText().toString())&&TextUtils.isEmpty(child_birthday.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter your first child!", Toast.LENGTH_LONG).show();
            return;
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ChildFragment childFragment = new ChildFragment();

        fragmentTransaction.add(R.id.container, childFragment);
        fragmentTransaction.commit();
        list.add(childFragment);

    }


}
