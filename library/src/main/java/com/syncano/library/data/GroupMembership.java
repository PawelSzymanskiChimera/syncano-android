package com.syncano.library.data;

import com.syncano.library.annotation.SyncanoField;

public class GroupMembership {

    public static final String FIELD_ID = "id";
    public static final String FIELD_USER = "user";

    @SyncanoField(name = FIELD_ID, readOnly = true)
    private int id;

    @SyncanoField(name = FIELD_USER)
    private User user;

    public GroupMembership() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}