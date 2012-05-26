package com.halusky.labs.activity;

import com.actionbarsherlock.app.SherlockActivity;
import com.evernote.client.conn.ApplicationInfo;
import com.evernote.client.oauth.android.EvernoteSession;
import com.halusky.labs.R;
import com.halusky.labs.utils.ConnectionUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HaluskyActivity extends SherlockActivity {

    private EditText mTxtEmail;
    private EditText mTxtPassword;
    private Button mBtnAuth;
    
    // Used to interact with the Evernote web service
    private EvernoteSession mSession;

    private View.OnClickListener mBtnAuthClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(HaluskyActivity.this, "Login", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        initLayoutResources();
        setupSession();
    }

    @Override
    public void onResume() {
      super.onResume();

      // Complete the Evernote authentication process if necessary
      if (!mSession.completeAuthentication()) {
        // We only want to do this when we're resuming after authentication...
        Toast.makeText(this, "Evernote login failed", Toast.LENGTH_LONG).show();
      }

      updateUiForLoginState();
    }
    private void initLayoutResources() {
        mTxtEmail = (EditText) findViewById(R.id.txtEmail);
        mTxtPassword = (EditText) findViewById(R.id.txtPassword);
        mBtnAuth = (Button) findViewById(R.id.btnAuth);
        mBtnAuth.setOnClickListener(mBtnAuthClickListener);
    }
    
    /**
     * Setup the EvernoteSession used to access the Evernote API.
     */
    private void setupSession() {
      ApplicationInfo info = 
        new ApplicationInfo(ConnectionUtils.CONSUMER_KEY, ConnectionUtils.CONSUMER_SECRET, 
                ConnectionUtils.EVERNOTE_HOST, ConnectionUtils.APP_NAME, ConnectionUtils.APP_VERSION);

      // TODO Retreived the cached Evernote AuthenticationResult if it exists
//      if (hasCachedEvernoteCredentials) {
//        AuthenticationResult result = new AuthenticationResult(authToken, noteStoreUrl, webApiUrlPrefix, userId);
//        session = new EvernoteSession(info, result, getTempDir());
//      } else {
        mSession = new EvernoteSession(info, ConnectionUtils.getTempDir());
//      }

        updateUiForLoginState();
    }
    
    /**
     * Update the UI based on Evernote authentication state.
     */
    private void updateUiForLoginState() {
      if (mSession.isLoggedIn()) {
          Toast.makeText(this, "Logged", Toast.LENGTH_SHORT).show();
//        btnAuth.setText(R.string.label_log_out);
//        btnSave.setEnabled(true);
//        btnSelect.setEnabled(true);
      } else {
          Toast.makeText(this, "=( not logged", Toast.LENGTH_SHORT).show();
//        btnAuth.setText(R.string.label_log_in);
//        btnSave.setEnabled(false);
//        btnSelect.setEnabled(false);
      }
    }
}