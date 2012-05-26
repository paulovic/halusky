package com.halusky.labs.utils;

import java.io.File;

import android.os.Environment;

public class ConnectionUtils {
    // A directory on disk where your application stores temporary data
    private static final String APP_DATA_PATH = 
      "/Android/data/com.halusky.labs.utils/tmp/";


    // Change to "www.evernote.com" to use the Evernote production service 
    // instead of the sandbox
    public static final String EVERNOTE_HOST = "sandbox.evernote.com";
    public static final String APP_NAME = "Halusky Android Sample";  
    public static final String APP_VERSION = "0.1";
    
    // Your Evernote API key. See http://dev.evernote.com/documentation/cloud/
    // Please obfuscate your code to help keep these values secret.
    public static final String CONSUMER_KEY = "paulovic";
    public static final String CONSUMER_SECRET = "99f5fef28fad5da0";

    /**
     * TODO this should be placed into another class
     */
    public static final String NOTE_PREFIX = 
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">" +
            "<en-note>";

          // The ENML postamble to every Evernote note 
    public static final String NOTE_SUFFIX = "</en-note>";

    /**
     * Get a temporary directory that can be used by this application to store potentially
     * large files sent to and retrieved from the Evernote API.
     */
    public static File getTempDir() {
      return new File(Environment.getExternalStorageDirectory(), APP_DATA_PATH);
    }
}
