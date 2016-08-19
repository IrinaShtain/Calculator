package shtainyky.com.calculator.integration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

import shtainyky.com.calculator.DisplayCalculator;
import shtainyky.com.calculator.MainActivity;
import shtainyky.com.calculator.users.DatabaseHelper;
import shtainyky.com.calculator.users.User;

public class FB_Integration extends AppCompatActivity {

    private static final String TAG = "mLog";
    private CallbackManager callbackManager;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,

                                            GraphResponse response) {
                                        User user;
                                        Log.e(TAG, response + "");
                                        try {
                                            user = new User();
                                            user.setLogin(object.getString("id"));
                                            Log.d(TAG, "object.getString(\"id\")" + object.getString("id"));
                                            user.setEmail(object.getString("email"));
                                            Log.d(TAG, "object.getString(\"email\")" + object.getString("email"));
                                            user.setFirst_name(object.getString("first_name"));
                                            Log.d(TAG, "object.getString(\"first_name\")" + object.getString("first_name"));
                                            user.setLast_name(object.getString("last_name"));
                                            Log.d(TAG, "object.getString(\"last_name\"" + object.getString("last_name"));
                                            user.setPhone(" ");
                                            user.setChildren(" ");
                                            user.setPassword("FB");
                                            if (helper.isUniquelogin(user.getLogin()))
                                            {
                                                helper.insertUser(user);
                                                Log.d(TAG, "isUniquelogin");
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                        Intent intent = new Intent(FB_Integration.this, DisplayCalculator.class);
                                        intent.putExtra(DisplayCalculator.INTEGRATION_WITH,SocialMediaProvider.FACEBOOK);
                                        startActivity(intent);
                                        finish();
                                    }

                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,first_name,last_name");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Intent intent = new Intent(FB_Integration.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
