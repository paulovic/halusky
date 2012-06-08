package com.halusky.labs.activity;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;

import com.actionbarsherlock.app.SherlockActivity;
import com.evernote.client.conn.ApplicationInfo;
import com.evernote.client.oauth.android.EvernoteSession;
import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteAttributes;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.util.EDAMUtil;
import com.halusky.labs.R;
import com.halusky.labs.utils.ConnectionUtils;
import com.halusky.labs.utils.NoteUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HaluskyActivity extends SherlockActivity {

    private EditText mTxtEmail;
    private EditText mTxtPassword;
    private Button mBtnAuth;
    
    private View.OnClickListener mBtnAuthClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startAuth();
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
      if (!ConnectionUtils.getSessionInstance().completeAuthentication()) {
        // We only want to do this when we're resuming after authentication...
        Toast.makeText(this, "Evernote login failed", Toast.LENGTH_LONG).show();
      }
      Toast.makeText(this, "Token: " + ConnectionUtils.getSessionInstance().getAuthToken(), Toast.LENGTH_LONG).show();
      updateUiForLoginState();
    }
    private void initLayoutResources() {
        mTxtEmail = (EditText) findViewById(R.id.txtEmail);
        mTxtPassword = (EditText) findViewById(R.id.txtPassword);
        mBtnAuth = (Button) findViewById(R.id.btnAuth);
        mBtnAuth.setOnClickListener(mBtnAuthClickListener);
        
        Button testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryListActivity.class);
                startActivity(intent);
            }
        });
        
        Button testButtonGrid = (Button) findViewById(R.id.testButtonGrid);
        testButtonGrid.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });        
    }
    
    /**
     * Setup the EvernoteSession used to access the Evernote API.
     */
    private void setupSession() {
      // TODO Retreived the cached Evernote AuthenticationResult if it exists
//      if (hasCachedEvernoteCredentials) {
//        AuthenticationResult result = new AuthenticationResult(authToken, noteStoreUrl, webApiUrlPrefix, userId);
//        session = new EvernoteSession(info, result, getTempDir());
//      }
        ConnectionUtils.connectFirstTime();
        updateUiForLoginState();
    }

    /**
     * Called when the user taps the "Select Image" button.
     * 
     * Sends the user to the image gallery to choose an image to share.
     */
    private void startAuth() {
      if (ConnectionUtils.getSessionInstance().isLoggedIn()) {
          ConnectionUtils.getSessionInstance().logOut();
      } else {
          ConnectionUtils.getSessionInstance().authenticate(this);
      }
      updateUiForLoginState();
    }

    /**
     * Update the UI based on Evernote authentication state.
     */
    private void updateUiForLoginState() {
      if (ConnectionUtils.getSessionInstance().isLoggedIn()) {
          Toast.makeText(this, "Logged", Toast.LENGTH_SHORT).show();
          Note note = new Note();
          note.setTitle("My test note number two");
          String content = 
                  NoteUtils.NOTE_PREFIX +
                  "<p>This note was uploaded from Android. It contains an image.</p>" +
                  NoteUtils.NOTE_SUFFIX;
          note.setContent(content);
          
          NoteAttributes attr = new NoteAttributes();
          //this makes it read only
          attr.setContentClass("halusky");
          note.setAttributes(attr);
          
          Notebook notebook = new Notebook();
          notebook.setName("Halusky" + System.currentTimeMillis());
          
          // Create the note on the server. The returned Note object
          // will contain server-generated attributes such as the note's
          // unique ID (GUID), the Resource's GUID, and the creation and update time.
          try {
              Notebook createNotebook = ConnectionUtils.getSessionInstance().createNoteStore().createNotebook(ConnectionUtils.getSessionInstance().getAuthToken(), notebook);
              note.setNotebookGuid(createNotebook.getGuid());
              Note createdNote = ConnectionUtils.getSessionInstance().createNoteStore().createNote(ConnectionUtils.getSessionInstance().getAuthToken(), note);
        } catch (TTransportException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (EDAMUserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (EDAMSystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (EDAMNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

      } else {
          Toast.makeText(this, "=( not logged", Toast.LENGTH_SHORT).show();
      }
    }
}