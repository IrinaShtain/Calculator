package shtainyky.com.calculator.integration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import shtainyky.com.calculator.DisplayCalculator;
import shtainyky.com.calculator.users.DatabaseHelper;
import shtainyky.com.calculator.users.User;

public class Google_Integration extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "mLog";
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount userAccount = result.getSignInAccount();
            String userId = userAccount.getId();
            String displayedUsername = userAccount.getDisplayName();
            String userEmail = userAccount.getEmail();
            User user = new User();
            user.setLogin(userId);
            Log.d(TAG, "object.getString(\"id\")" + userId);
            user.setEmail(userEmail);
            Log.d(TAG, "object.getString(\"email\")" + userEmail);
            user.setFirst_name(displayedUsername);
            Log.d(TAG, "object.getString(\"first_name\")" + displayedUsername);
            user.setLast_name(" ");
            Log.d(TAG, "object.getString(\"last_name\"" + "last_name");
            user.setPhone(" ");
            user.setChildren(" ");
            user.setPassword("Google");
           if (helper.isUniquelogin(user.getLogin()))
            {
                helper.insertUser(user);
                Log.d(TAG, "isUniquelogin");
            }
            Intent intent = new Intent(Google_Integration.this, DisplayCalculator.class);
            intent.putExtra(DisplayCalculator.INTEGRATION_WITH,SocialMediaProvider.GOOGLE);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }



}

