package com.tasks.to.todotasks.db;

/**
 * Created by richa on 3/17/16.
 */


    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.renderscript.RenderScript;
    import android.util.Log;

public class DBHelper  extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, TaskContract.DB_NAME, null, TaskContract.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqlDB) {
            String sqlQuery =
                    String.format("CREATE TABLE %s (" +
                                        TaskContract.Columns._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "%s VARCHAR,"+"%s VARCHAR," +" %s VARCHAR,"+ "%s VARCHAR)", TaskContract.TABLE,
                            TaskContract.Columns.TASK,TaskContract.Columns.About,TaskContract.Columns.Priority,TaskContract.Columns.Category);

            Log.d("TaskDBHelper","Query to form table: "+sqlQuery);
            sqlDB.execSQL(sqlQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
            sqlDB.execSQL("DROP TABLE IF EXISTS "+TaskContract.TABLE);
            onCreate(sqlDB);
        }
    }

