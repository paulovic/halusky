package com.halusky.labs.utils;

public class NoteUtils {
    public static final String NOTE_PREFIX = 
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">" +
            "<en-note>";

    // The ENML postamble to every Evernote note 
    public static final String NOTE_SUFFIX = "</en-note>";
}
