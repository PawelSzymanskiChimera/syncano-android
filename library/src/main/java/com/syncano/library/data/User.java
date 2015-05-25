package com.syncano.library.data;

import com.syncano.library.annotation.SyncanoField;
import com.syncano.library.choice.RuntimeName;

import org.json.JSONObject;

public class User {

    public static final String FIELD_ID = "id";
    public static final String FIELD_USER_NAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_USER_KEY = "user_key";
    public static final String FIELD_PROFILE = "profile";

    @SyncanoField(name = FIELD_ID, readOnly = true)
    private int id;

    @SyncanoField(name = FIELD_USER_NAME, required = true)
    private String userName;

    @SyncanoField(name = FIELD_PASSWORD, required = true)
    private String password;

    @SyncanoField(name = FIELD_USER_KEY, readOnly = true)
    private String userKey;

    @SyncanoField(name = FIELD_PROFILE, readOnly = true)
    private Profile profile;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}