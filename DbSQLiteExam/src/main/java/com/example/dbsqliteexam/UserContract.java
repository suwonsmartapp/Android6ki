package com.example.dbsqliteexam;

import android.provider.BaseColumns;

/**
 * 스키마 정의
 */
public final class UserContract {
    private UserContract() {
    }

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
