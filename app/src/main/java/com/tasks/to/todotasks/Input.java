package com.tasks.to.todotasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tasks.to.todotasks.db.DBHelper;
import com.tasks.to.todotasks.db.TaskContract;

public class Input extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        final EditText topi = (EditText) findViewById(R.id.topic1);
        final EditText about = (EditText) findViewById(R.id.about1);

        Spinner priorityspinner = (Spinner) findViewById(R.id.priorityspinner);
        ArrayAdapter<CharSequence> adapterp = ArrayAdapter.createFromResource(this,
                R.array.prioritylist, android.R.layout.simple_spinner_item);
        adapterp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priorityspinner.setAdapter(adapterp);

        Spinner categoryspinner = (Spinner) findViewById(R.id.categoryspinner);
        ArrayAdapter<CharSequence> adapterc = ArrayAdapter.createFromResource(this,
                R.array.categorylist, android.R.layout.simple_spinner_item);
        adapterc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryspinner.setAdapter(adapterc);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();
                String task = about.getText().toString();
                String topic = topi.getText().toString();


                Spinner priorityspinner = (Spinner) findViewById(R.id.priorityspinner);
                String priority = priorityspinner.getSelectedItem().toString();

                Spinner categoryspinner = (Spinner) findViewById(R.id.categoryspinner);
                String category = priorityspinner.getSelectedItem().toString();

                Log.d("MainActivity", topic);


                if(priority.isEmpty() || category.isEmpty() || task.isEmpty() || topic.isEmpty())
                {
                    //Snackbar.make(view, "Enter Valid values", Snackbar.LENGTH_LONG)
                     //.setAction("Action", null).show();
                   // 1. Instantiate an AlertDialog.Builder with its constructor
                    //topic.setText("enfjewnb");
                    AlertDialog.Builder builder = new AlertDialog.Builder(Input.this);
                    builder.setMessage("Enter valid values")
                            .setTitle("Empty fields");
                    AlertDialog dialog = builder.create();
                    dialog.show();


                }
                else{
                DBHelper helper = new DBHelper(Input.this);
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.clear();
                values.put(TaskContract.Columns.TASK, topic);

               values.put ( TaskContract.Columns.About,task);
              //  values.put(TaskContract.Columns.Priority,priority);
               // values.put (TaskContract.Columns.Category,category);

                db.insert(TaskContract.TABLE, null, values);
               finish();
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

   /* @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Input Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.tasks.to.todotasks/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Input Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.tasks.to.todotasks/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }*/
}
