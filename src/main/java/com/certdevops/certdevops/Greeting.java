package com.certdevops.certdevops;

public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id; // unique identifier for the greeting
        this.content = content; // textual representation of the greeting
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
