package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class User implements Serializable {
    private static final String TAG = makeLogTag(User.class);

    private String _id;
    private String _name;

    public User() {
    }

    public User(String id, String name) {
        this._id = id;
        this._name = name;
    }

    public void setID(String id) {
        this._id = id;
    }

    public String getID() {
        return this._id;
    }

    public void setName( String name) {
        this._name = name;
    }

    public String getName() { return this._name; }
}