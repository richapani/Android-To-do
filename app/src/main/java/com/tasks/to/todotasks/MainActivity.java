package com.tasks.to.todotasks;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tasks.to.todotasks.db.DBHelper;
import com.tasks.to.todotasks.db.TaskContract;

public class MainActivity extends ListActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         //setSupportActionBar(toolbar);
        //Toolbar.setTi


        uiupdate();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();
                Intent myIntent = new Intent(MainActivity.this, Input.class);

                MainActivity.this.startActivity(myIntent);

                uiupdate();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }
   // @Override
   // public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // DBHelper helper = new DBHelper(MainActivity.this);
        //SQLiteDatabase sqlDB = helper.getReadableDatabase();

        //String item = l.getItemAtPosition(position).toString();
        //TextView t=(TextView)findViewById(R.id.g);
        //t.setText(item);
    //}

    @Override
    public void onResume() {
        super.onResume();
        uiupdate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        ListView l =(ListView)findViewById(android.R.id.list);
        l.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                TextView t=(TextView)findViewById(R.id.g);
                t.setText("ghgrhg");
        }});
        return super.onOptionsItemSelected(item);
    }//*/


    private void uiupdate() {
        DBHelper helper = new DBHelper(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(TaskContract.TABLE,
                new String[]{TaskContract.Columns._ID, TaskContract.Columns.TASK},
                null, null, null, null, null);

        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.viewtask,
                cursor,
                new String[]{TaskContract.Columns.TASK},
                new int[]{R.id.taskView},
                0
        );
        this.setListAdapter(listAdapter);
    }


    public void onDeleteButtonClick(View view) {
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskView);
        String task = taskTextView.getText().toString();

        String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                TaskContract.TABLE,
                TaskContract.Columns.TASK,
                task);


       DBHelper helper = new DBHelper(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
        sqlDB.execSQL(sql);
        uiupdate();
    }
    public void onViewClick(View view) {
        View v = (View) view.getParent();
       TextView taskTextView = (TextView) v.findViewById(R.id.taskView);
        String task = taskTextView.getText().toString();
        DBHelper helper = new DBHelper(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();

        String sql = String.format("SELECT * FROM %s WHERE %s = '%s'",
                TaskContract.TABLE,
                TaskContract.Columns.TASK,
                task);
        Cursor cursor = sqlDB.rawQuery(sql, null);
        cursor.moveToFirst();
        //TextView t=(TextView)findViewById(R.id.g);
        //t.setText(cursor.getString(4));
        String topic=cursor.getString(1);
        String about=cursor.getString(2);
        String priority=cursor.getString(3);
        String category=cursor.getString(4);
        Intent showIntent = new Intent(MainActivity.this, Show.class);
        showIntent.putExtra("topic",topic);
        showIntent.putExtra("about",about);
        showIntent.putExtra("priority",priority);
        showIntent.putExtra("category",category);
        MainActivity.this.startActivity(showIntent);

    }




   /* */
}