package com.example.myapplication;

public class url {
    private String mName;
    private int mLink;

    public url(String name, int link) {

        this.mName = name;
        this.mLink = link;
    }

    public int getLink() {

        return mLink;
    }

    public void setLink(int link) {

        this.mLink = link;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        this.mName = name;
    }

    @Override
    public String toString() {

        return this.mName;
    }
}
