package com.tasks.to.todotasks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Show extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("TASK INFORMATION");

        Bundle extras = getIntent().getExtras();
        String topic = null;
        String about = null;
        String priority = null;
        String category = null;
        if (extras != null) {
            topic = extras.getString("topic");
            about = extras.getString("about");
            priority = extras.getString("priority");
            category = extras.getString("category");
        }
        TextView t = (TextView) findViewById(R.id.topicc);
        t.setText(topic);
        TextView a = (TextView) findViewById(R.id.aboutt);
        a.setText(about);
        TextView p = (TextView) findViewById(R.id.priorityy);
        p.setText(priority);
        TextView c = (TextView) findViewById(R.id.categoryy);
        c.setText(category);
    }
public  void onClick(View view)
{
    finish();
}

}
