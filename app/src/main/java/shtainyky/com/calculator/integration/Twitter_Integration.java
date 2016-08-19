package shtainyky.com.calculator.integration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import io.fabric.sdk.android.Fabric;
import shtainyky.com.calculator.DisplayCalculator;
import shtainyky.com.calculator.MainActivity;
import shtainyky.com.calculator.R;
import shtainyky.com.calculator.users.DatabaseHelper;
import shtainyky.com.calculator.users.User;

public class Twitter_Integration extends AppCompatActivity {

    private static final String TAG = "mLog";
    private TwitterAuthClient client;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(this);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_app_key), getString(R.string.twitter_app_secret));
        Fabric.with(this, new Twitter(authConfig));
        client = new TwitterAuthClient();
        client.authorize(Twitter_Integration.this, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                login(twitterSessionResult);
                Intent intent = new Intent(Twitter_Integration.this, DisplayCalculator.class);
                intent.putExtra(DisplayCalculator.INTEGRATION_WITH, SocialMediaProvider.TWITTER);
                startActivity(intent);
                finish();
            }

            @Override
            public void failure(TwitterException e) {
                Intent intent = new Intent(Twitter_Integration.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void login(Result<TwitterSession> twitterSessionResult) {
        TwitterSession session = twitterSessionResult.data;
        User user = new User();
        user.setLogin(String.valueOf(session.getId()));
        Log.d(TAG, "object.getString(\"id\")" + String.valueOf(session.getId()));
        user.setEmail(" ");
        Log.d(TAG, "object.getString(\"email\")" + "+");
        user.setFirst_name(session.getUserName());
        Log.d(TAG, "object.getString(\"first_name\")" + session.getUserName());
        user.setLast_name(" ");
        Log.d(TAG, "object.getString(\"last_name\"" + "last_name");
        user.setPhone(" ");
        user.setChildren(" ");
        user.setPassword("Twitter");
        if (helper.isUniquelogin(user.getLogin()))
        {
            helper.insertUser(user);
            Log.d(TAG, "isUniquelogin");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        client.onActivityResult(requestCode, resultCode, data);
    }
}

