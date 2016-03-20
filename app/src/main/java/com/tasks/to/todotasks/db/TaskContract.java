package com.tasks.to.todotasks.db;

/**
 * Created by richa on 3/17/16.
 */
import android.provider.BaseColumns;

public class TaskContract {

    public static final String DB_NAME = "com.example.todotasks.db.tasks";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "tasks";

    public class Columns {
        public static final String TASK="topic" ;
        public static final String About="about";
        public static final String Priority="priority";
        public static final String Category="category";
        public static final String _ID = BaseColumns._ID;
    }
}
