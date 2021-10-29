package com.project.datahandlingtute.database;
import android.provider.BaseColumns;
//defines the structure of the database
public final class UsersMaster {
    private UsersMaster(){

    }
    //inner static class in the UsersMaster class
    public static class Users implements BaseColumns{
        public static final String TABLE_NAME="users";
        public static final String COLUMN_NAME_USERNAME="username";
        public static final String COLUMN_NAME_PASSWORD="password";


    }
}
